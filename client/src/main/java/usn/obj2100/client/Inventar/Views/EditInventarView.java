package usn.obj2100.client.Inventar.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import usn.obj2100.client.ClientController;
import usn.obj2100.client.GUI.EditTextField;
import usn.obj2100.client.GUI.KategoriCombo;
import usn.obj2100.client.GUI.TypeCombo;
import usn.obj2100.shared.model.Inventar;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import usn.obj2100.shared.model.InventarExtended;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Denne klassen representerer en visning for redigering av inventarobjekter i systemet.
 * Brukergrensesnittet lar brukeren endre informasjon om et eksisterende inventar, inkludert type, kategori,
 * innkjøpsdato, pris, og mer.
 * <p>
 * Visningen inkluderer dynamisk innhold basert på inventartypen, spesielt med hensyn til håndtering av levetid
 * for møbler sammenlignet med andre typer inventar.
 * <p>
 * Eksempel på bruk:
 * <pre>
 * {@code
 * ClientController clientController = new ClientController();
 * EditInventarView editInventarView = new EditInventarView(clientController);
 * }
 * </pre>
 *
 * @param clientController Klientkontrolleren som håndterer interaksjoner og tilgang til det valgte inventaret.
 */
public class EditInventarView extends HBox
{
		private InventarExtended selectedInventar;
		final private ClientController mc;
	/**
	 * Oppretter en ny visning for redigering av inventar.
	 * Initialiserer komponentene basert på det valgte inventaret hentet fra klientkontrolleren.
	 * Dersom det ikke er valgt et inventar, vil en feilmelding vises.
	 *
	 * @param clientController Klientkontrolleren som benyttes for å håndtere og hente det valgte inventaret.
	 */
		public EditInventarView(ClientController clientController){
			this.mc = clientController;
			this.selectedInventar = mc.getSelectedInvetar();
			getChildren().add(renderEditScreen());
			
			if(selectedInventar != null){
				renderEditScreen();
			}else{
				renderNoSelection();
			}
			
		}
	/**
	 * Oppretter en visning som informerer brukeren om at ingen inventar er valgt.
	 * Dette skjermet inkluderer en feilmelding og en knapp for å returnere til startskjermen.
	 *
	 * @return En VBox med feilmelding og returknapp.
	 */
		private VBox renderNoSelection(){
			VBox form = new VBox(10);
			form.setPadding(new Insets(20));
			form.setAlignment(Pos.CENTER);
			form.setSpacing(20);
			
			Label noSelectionError = new Label("Du har ikke valg et inventar!");
			noSelectionError.setFont(Font.font(25));
			noSelectionError.setAlignment(Pos.CENTER);
			
			Button returnerTilStart = new Button("Returner");
			returnerTilStart.setTooltip(new Tooltip("Returner til startskjerm"));
			returnerTilStart.setAlignment(Pos.CENTER);
			returnerTilStart.getStyleClass().add("backToStartButton");
			
			form.getChildren().addAll(noSelectionError, returnerTilStart);
			
			return form;
		}

	/**
	 * Oppretter og returnerer hovedgrensesnittet for redigering av inventar.
	 * Inkluderer inndatafelt for type, kategori, dato, pris og andre relevante detaljer.
	 * Avhenger av valgt type for å tilpasse hvilke felt som er aktive eller relevante.
	 *
	 * @return En VBox med alle redigeringsfeltene.
	 */
		private VBox renderEditScreen() {
			VBox form = new VBox(10);
			form.setPadding(new Insets(20));
			form.setAlignment(Pos.CENTER);
			String[] typer = {"Møbler", "Utsmykning", "Teknisk Utstyr"};
			String selectedType = "Møbler"; // TODO gjøre dynamisk, skal lastes fra db..
			TypeCombo<String> typeComboBox = new TypeCombo(typer, selectedType);
			
			
			KategoriCombo<String> categoryComboBox = new KategoriCombo<>(); //TODO gjøre dynamisk!!!
			categoryComboBox.setPrefWidth(200); // Set preferred width for categoryComboBox
			
			
			
			DatePicker purchaseDatePicker = new DatePicker();
			purchaseDatePicker.setPrefWidth(200); // Ensure consistent width for purchaseDatePicker
			EditTextField priceField = new EditTextField("" + selectedInventar.getInnkjopspris());
			EditTextField locationField = new EditTextField("" +selectedInventar.getPlassering());
			EditTextField quantityField = new EditTextField("" + selectedInventar.getAntall());
			EditTextField lifespanField = new EditTextField("" + selectedInventar.getForventetLevetid());
			lifespanField.setDisable(true); // Deaktiveres inntil den er nødvendig
			
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			TextArea descriptionField = new TextArea();
			descriptionField.setPrefRowCount(3); // Set preferred row count for description field
			
			
			grid.addRow(0, new Label("Type:"), typeComboBox);
			grid.addRow(1, new Label("Kategori:"), categoryComboBox);
			grid.addRow(2, new Label("Innkjøpsdato:"), purchaseDatePicker);
			grid.addRow(3, new Label("Innkjøpspris:"), priceField);
			grid.addRow(4, new Label("Plassering:"), locationField);
			grid.addRow(5, new Label("Antall:"), quantityField);
			grid.addRow(6, new Label("Forventet levetid (kun møbler):"), lifespanField);
			grid.addRow(7, new Label("Beskrivelse:"), descriptionField);
			
			typeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
				categoryComboBox.getItems().clear();
				if ("Møbler".equals(newVal)) {
					String[] moblerKatTemp = {"Bord", "Stol", "Sofa", "Skap", "Hylle", "Tavle", "Annet"};
					categoryComboBox.setKategorier(moblerKatTemp);
					categoryComboBox.setEditable(false);
					lifespanField.setDisable(false);
				} else if ("Utsmykning".equals(newVal)) {
					String[] utSmyKatTemp = {"Maleri", "Grafikk", "Tekstil", "Bilde", "Skulptur", "Annet"};
					categoryComboBox.getItems().addAll(utSmyKatTemp);
					categoryComboBox.setEditable(false);
					lifespanField.setDisable(true);
				} else if ("Teknisk Utstyr".equals(newVal)) {
					String[] tekKatTemp = {"pc"};
					categoryComboBox.setEditable(true);
					lifespanField.setDisable(true);
				}
			});
			Button addButton = new Button("Legg til");
			addButton.setOnAction(event -> {
				try {
					LocalDate purchaseDate = purchaseDatePicker.getValue();
					double price = Double.parseDouble(priceField.getText());
					int quantity = Integer.parseInt(quantityField.getText());
					Integer lifespan = "Møbler".equals(typeComboBox.getValue()) ? Integer.parseInt(lifespanField.getText()) : null;
					String description = descriptionField.getText();
					categoryComboBox.setPrefWidth(200); // Set preferred width for categoryComboBox
					
					purchaseDatePicker.setPrefWidth(200); // Ensure consistent width for purchaseDatePicker
					
					// FIXME: Use Shared model Inventar.
					//InventarElement newElement = new InventarElement(
					//	typeComboBox.getValue(),
					//	categoryComboBox.getValue(),
					//	description,
					//
					//	purchaseDate.toString(),
					//	price,
					//	locationField.getText(),
					//	quantity,
					//	lifespan
					//);
					LocalDateTime localDateTime = LocalDateTime.now();




					Inventar inventar = new Inventar( 12344444, description, localDateTime, price, quantity, lifespan, 1, 1, 1);
					//TODO insert into db here !

					System.out.println(inventar);
					try {
						mc.getInventarController().editInventar(inventar);
					} catch (Exception e){
						System.out.println(e);
					}
				} catch (NumberFormatException e) {
					new Alert(Alert.AlertType.ERROR, "Sjekk at alle tallfelt er korrekt fylt ut.").show();
				}
			});
			
			
			form.getChildren().addAll(grid, addButton);
			return form;
		}
}

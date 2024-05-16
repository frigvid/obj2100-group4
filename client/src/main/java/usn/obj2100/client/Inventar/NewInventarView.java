package usn.obj2100.client.Inventar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import usn.obj2100.client.ClientController;

import java.time.LocalDate;


/**
 * Denne klassen representerer et visningsområde for å legge til nye inventarobjekter i systemet.
 * Dette inkluderer brukergrensesnittkomponenter for å inntaste informasjon som type, kategori, innkjøpsdato, pris, og mer.
 * <p>
 * Brukergrensesnittet organiseres primært gjennom en VBox som inneholder et GridPane for inndatafeltene og en knapp for å legge til inventaret.
 * Hver felttype har tilhørende logikk for å håndtere ulike datatyper korrekt, som tall og datoer.
 * <p>
 * Eksempel på bruk:
 * <pre>
 * {@code
 * ClientController clientController = new ClientController();
 * NewInventarView newInventarView = new NewInventarView(clientController);
 * }
 * </pre>
 *
 * @param mc Klientkontrolleren som brukes for interaksjon med resten av klientapplikasjonen.
 * @see VBox
 * @see GridPane
 */
public class NewInventarView extends HBox
{
	private ClientController mc;


	/**
	 * Konstruerer et nytt visningsvindu for å legge til inventar.
	 * Dette kaller {@link #buildAddForm()} for å bygge brukergrensesnittet hvor brukeren kan inntaste informasjon.
	 *
	 * @param mc Klientkontrolleren som tilrettelegger for nødvendige operasjoner og interaksjoner.
	 */
	public NewInventarView(ClientController mc){
		this.mc = mc;
		getChildren().add(buildAddForm());
	}

	/**
	 * Bygger skjemaet som brukeren interagerer med for å legge til nytt inventar.
	 * Skjemaet inkluderer felt for type, kategori, innkjøpsdato, pris, plassering, antall, forventet levetid og en beskrivelse.
	 * <p>
	 * Avhengig av valgt type, tilpasses kategorivalgene og muligheten til å angi levetid.
	 *
	 * @return En VBox som inneholder alle GUI-komponenter for å legge til nytt inventar.
	 */
	private VBox buildAddForm() {
		VBox form = new VBox(10);
		form.setPadding(new Insets(20));
		form.setAlignment(Pos.CENTER);
		
		ComboBox<String> typeComboBox = new ComboBox<>();
		typeComboBox.getItems().addAll("Mobler", "Utsmykning", "Teknisk Utstyr");
		
		ComboBox<String> categoryComboBox = new ComboBox<>();
		categoryComboBox.setPrefWidth(200); // Set preferred width for categoryComboBox
		DatePicker purchaseDatePicker = new DatePicker();
		purchaseDatePicker.setPrefWidth(200); // Ensure consistent width for purchaseDatePicker
		TextField priceField = new TextField();
		TextField locationField = new TextField();
		TextField quantityField = new TextField();
		TextField lifespanField = new TextField();
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
			if ("Mobler".equals(newVal)) {
				categoryComboBox.getItems().addAll("Bord", "Stol", "Sofa", "Skap", "Hylle", "Tavle", "Annet");
				categoryComboBox.setEditable(false);
				lifespanField.setDisable(false);
			} else if ("Utsmykning".equals(newVal)) {
				categoryComboBox.getItems().addAll("Maleri", "Grafikk", "Tekstil", "Bilde", "Skulptur", "Annet");
				categoryComboBox.setEditable(false);
				lifespanField.setDisable(true);
			} else if ("Teknisk Utstyr".equals(newVal)) {
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
				Integer lifespan = "Mobler".equals(typeComboBox.getValue()) ? Integer.parseInt(lifespanField.getText()) : null;
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
					
					//TODO insert into db here !
				//mc.getInventarController().addInventar(inventar);
			} catch (NumberFormatException e) {
				new Alert(Alert.AlertType.ERROR, "Sjekk at alle tallfelt er korrekt fylt ut.").show();
			}
		});
		
		
		form.getChildren().addAll(grid, addButton);
		return form;
	}
	
	
	
}

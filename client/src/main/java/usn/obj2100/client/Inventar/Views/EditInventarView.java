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

import java.time.LocalDate;

public class EditInventarView extends HBox
{
		private Inventar selectedInventar;
		final private ClientController mc;
		
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
					
					//TODO insert into db here !
				} catch (NumberFormatException e) {
					new Alert(Alert.AlertType.ERROR, "Sjekk at alle tallfelt er korrekt fylt ut.").show();
				}
			});
			
			
			form.getChildren().addAll(grid, addButton);
			return form;
		}
}

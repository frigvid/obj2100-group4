package usn.obj2100;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.ResultSet;
import usn.obj2100.Search.SearchBarView;

public class ClientView {
	private BorderPane root;
	private TabPane tabs;
	private StackPane mainContent;

	private InventarSearch inventarSearch;
	private SearchBarView searchBarView;
	private DatabaseManager dbManager;


	public ClientView(BorderPane root, DatabaseManager dbManager) {
		this.root = root;
		this.dbManager = dbManager;
		this.tabs = new TabPane();
		this.mainContent = new StackPane();

		mainContent.setAlignment(Pos.TOP_LEFT);
		initializeTabs();
		root.setCenter(tabs);

		String css = getClass().getResource("/style.css").toExternalForm();



		this.mainContent.getChildren().add(tabs); //stackpane kan legge elementer oppå hverandre, searchbar er oppå tabs

		root.setCenter(mainContent);
		root.getStylesheets().add("search.css");
	}

	private void initializeTabs() {
		tabs.getTabs().addAll(
			new Tab("Legg til nytt element", buildAddForm()),
			new Tab("Søk", new Label("Søk etter inventar..."))
		);
	}

	private VBox buildAddForm() {
		VBox form = new VBox(10);
		form.setPadding(new Insets(20));
		form.setAlignment(Pos.CENTER);

		ComboBox<String> typeComboBox = new ComboBox<>();
		typeComboBox.getItems().addAll("Møbler", "Utsmykning", "Teknisk Utstyr");

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
			if ("Møbler".equals(newVal)) {
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
				Integer lifespan = "Møbler".equals(typeComboBox.getValue()) ? Integer.parseInt(lifespanField.getText()) : null;
				String description = descriptionField.getText();
				categoryComboBox.setPrefWidth(200); // Set preferred width for categoryComboBox

				purchaseDatePicker.setPrefWidth(200); // Ensure consistent width for purchaseDatePicker

				InventarElement newElement = new InventarElement(
					typeComboBox.getValue(),
					categoryComboBox.getValue(),
					description,

					purchaseDate.toString(),
					price,
					locationField.getText(),
					quantity,
					lifespan
				);
				dbManager.insertInventar(newElement);
			} catch (NumberFormatException e) {
				new Alert(Alert.AlertType.ERROR, "Sjekk at alle tallfelt er korrekt fylt ut.").show();
			}
		});


		form.getChildren().addAll(grid, addButton);
		return form;
	}

	public TabPane getTabs() {
		return tabs;
	}

	public void setTab (int index) {
		tabs.getSelectionModel().select(index);
	}

	public void setNewTabContent ( Node content) {
		Tab tempTab = new Tab("Nytt Søk", content);
		tabs.getTabs().add(tempTab);
		setTab(tabs.getTabs().size()-1);
	}

	public void updateTabContent ( Node content) {
		tabs.getSelectionModel().getSelectedItem().setContent(content);
	}


	public StackPane getMainContent() {
		return mainContent;
	}

	public SearchBarView getSearchBarView() {
		return searchBarView;
	}

	public void setSearchBarView(SearchBarView searchBarView) {
		this.searchBarView = searchBarView;
	}

}

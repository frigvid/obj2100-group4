package usn.obj2100.client.Search;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import usn.obj2100.shared.model.Inventar;

import java.util.Collections;
import java.util.Comparator;

public class SearchResultView extends VBox {
	private SearchController searchController;
	private javafx.collections.ObservableList<Inventar> searchResults;
	private boolean sortAscendingType = true;
	private boolean sortAscendingKategori = true;
	private boolean sortAscendingBeskrivelse = true;
	private boolean sortAscendingInnkjøpsPris = true;
	private boolean sortAscendingInnkjøpsDato = true;


	public SearchResultView(SearchController searchController) {
		this.searchController = searchController;
		initSearchResultView();
	}
	private void initSearchResultView() {
		// Load the CSS file
		getStylesheets().add("/style.css");

		// Initialize search results
		searchResults = FXCollections.observableArrayList(searchController.getSearchResults());

		// Create the GridPane for search results
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new javafx.geometry.Insets(10));

		// Create the header row
		//Label typeHeader = createStyledLabelText("Type");
		Label kategoriHeader = createStyledLabelText("Kategori");
		Label beskrivelseHeader = createStyledLabelText("Beskrivelse");
		Label innkjøpsPrisHeader = createStyledLabelText("Innkjøps Pris");
		Label innkjøpsDatoHeader = createStyledLabelText("Innkjøps Dato");

		Label actionsHeader = new Label("Actions"); // No need to style the header's action column

		//typeHeader.setStyle("-fx-text-alignment: center;");
		//typeHeader.setOnMouseClicked(event -> sortResults("type"));

		kategoriHeader.setStyle("-fx-text-alignment: center;");
		kategoriHeader.setOnMouseClicked(event -> sortResults("header"));

		beskrivelseHeader.setStyle("-fx-text-alignment: center;");
		beskrivelseHeader.setOnMouseClicked(event -> sortResults("beskrivelse"));

		innkjøpsPrisHeader.setStyle("-fx-text-alignment: center;");
		innkjøpsPrisHeader.setOnMouseClicked(event -> sortResults("innkjøpsPris"));

		innkjøpsDatoHeader.setStyle("-fx-text-alignment: center;");
		innkjøpsDatoHeader.setOnMouseClicked(event -> sortResults("innkjøpsDato"));

		// Add header labels to the grid pane
		//gridPane.add(typeHeader, 0, 0);
		gridPane.add(kategoriHeader, 1, 0);
		gridPane.add(beskrivelseHeader, 2, 0);
		gridPane.add(innkjøpsPrisHeader, 3, 0);
		gridPane.add(innkjøpsDatoHeader, 4, 0);
		gridPane.add(actionsHeader, 5, 0);

		// Style the header row
		//typeHeader.getStyleClass().add("header-label");
		kategoriHeader.getStyleClass().add("header-label");
		beskrivelseHeader.getStyleClass().add("header-label");
		innkjøpsPrisHeader.getStyleClass().add("header-label");
		innkjøpsDatoHeader.getStyleClass().add("header-label");
		actionsHeader.getStyleClass().add("header-label");

		// Create a ScrollPane to hold the GridPane
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(gridPane);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);

		// Add the grid pane to the parent container
		getChildren().add(scrollPane);

		// Display the initial search results
		displayResults(gridPane);
	}

	private void displayResults(GridPane gridPane) {
		gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);

		int rowIndex = 1;
		for (Inventar inventar : searchResults) {
			//Label typeLabel = createStyledLabel(inventar.getType());
			Label kategoriLabel = createStyledLabel(inventar.getKategori().getKategoriNavn());
			Label beskrivelseLabel = createStyledLabel(inventar.getBeskrivelse());
			Label innkjøpsPrisLabel = createStyledLabel(inventar.getInnkjøpsPris() + " kr");
			Label innkjøpsDatoLabel = createStyledLabel(inventar.getInnkjøpsDato().toString());
			HBox actionsBox = createActionButtons(inventar);

			// Add the data labels and actions box to the grid pane
			//gridPane.add(typeLabel, 0, rowIndex);
			gridPane.add(kategoriLabel, 1, rowIndex);
			gridPane.add(beskrivelseLabel, 2, rowIndex);
			gridPane.add(innkjøpsPrisLabel, 3, rowIndex);
			gridPane.add(innkjøpsDatoLabel, 4, rowIndex);
			gridPane.add(actionsBox, 5, rowIndex);

			// Style the data row
			//typeLabel.getStyleClass().add("data-label");
			kategoriLabel.getStyleClass().add("data-label");
			beskrivelseLabel.getStyleClass().add("data-label");
			innkjøpsPrisLabel.getStyleClass().add("data-label");
			innkjøpsDatoLabel.getStyleClass().add("data-label");
			actionsBox.getStyleClass().add("data-row");

			gridPane.getChildren().get(rowIndex).getStyleClass().add("data-row");

			rowIndex++;
		}
	}

	private void sortResults(String column) {
		Comparator<Inventar> comparator = null;
		boolean sortAscending = true;

		switch (column) {
			case "type":
				comparator = Comparator.comparing(Inventar::getType);
				sortAscending = sortAscendingType;
				sortAscendingType = !sortAscendingType;
				break;
			case "kategori":
				comparator = Comparator.comparing(inventar -> inventar.getKategori().getKategoriNavn());
				sortAscending = sortAscendingKategori;
				sortAscendingKategori = !sortAscendingKategori;
				break;
			case "beskrivelse":
				comparator = Comparator.comparing(Inventar::getBeskrivelse);
				sortAscending = sortAscendingBeskrivelse;
				sortAscendingBeskrivelse = !sortAscendingBeskrivelse;
				break;
			case "innkjøpsPris":
				comparator = Comparator.comparing(Inventar::getInnkjøpsPris);
				sortAscending = sortAscendingInnkjøpsPris;
				sortAscendingInnkjøpsPris = !sortAscendingInnkjøpsPris;
				break;
			case "innkjøpsDato":
				comparator = Comparator.comparing(Inventar::getInnkjøpsDato);
				sortAscending = sortAscendingInnkjøpsDato;
				sortAscendingInnkjøpsDato = !sortAscendingInnkjøpsDato;
				break;
		}

		if (comparator != null) {
			if (sortAscending) {
				Collections.sort(searchResults, comparator);
			} else {
				Collections.sort(searchResults, comparator.reversed());
			}
			displayResults((GridPane) ((ScrollPane) getChildren().get(0)).getContent());
		}
	}




	// Method to create styled labels
	private Label createStyledLabel(String text) {
		Label label = new Label(text);
		label.getStyleClass().add("data-label");
		return label;
	}

	private Label createStyledLabelText(String text) {
		Label label = new Label(text);
		label.getStyleClass().add("data-label-text");
		return label;
	}

	// Method to create action buttons for each row
	private HBox createActionButtons(Inventar inventar) {
		Button viewButton = new Button("View");
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");

		// Add CSS class to buttons
		viewButton.getStyleClass().add("action-button");
		editButton.getStyleClass().add("action-button");
		deleteButton.getStyleClass().add("action-button");

		// Add event handlers for buttons
		viewButton.setOnAction(e -> handleViewAction(inventar));
		editButton.setOnAction(e -> handleEditAction(inventar));
		deleteButton.setOnAction(e -> handleDeleteAction(inventar));

		HBox actionButtons = new HBox(viewButton, editButton, deleteButton);
		actionButtons.setSpacing(5);
		actionButtons.getStyleClass().add("button-container");
		return actionButtons;
	}

	// Event handler methods for buttons
	private void handleViewAction(Inventar inventar) {
		// Implement the view action
		System.out.println("Viewing: " + inventar.getBeskrivelse());
	}

	private void handleEditAction(Inventar inventar) {
		// Implement the edit action
		System.out.println("Editing: " + inventar.getBeskrivelse());
	}

	private void handleDeleteAction(Inventar inventar) {
		// Implement the delete action
		System.out.println("Deleting: " + inventar.getBeskrivelse());
	}


}

package usn.obj2100.Search;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import usn.obj2100.Inventar.Inventar;

public class SearchResultView extends VBox {
	private SearchController searchController;
	public SearchResultView(SearchController searchController) {
		this.searchController = searchController;
		initSearchResultView();
	}
	private void initSearchResultView() {
		// Load the CSS file

		getStylesheets().add("/style.css");

		// Create the header row
		HBox headerRow = new HBox();
		headerRow.getChildren().addAll(
			createStyledLabel("Beskrivelse"),
			createStyledLabel("Innkjøps Pris"),
			createStyledLabel("Innkjøps Dato"),
			new Label("Actions") // No need to style the header's action column
		);
		headerRow.setSpacing(10);
		headerRow.setPadding(new javafx.geometry.Insets(10));
		headerRow.getStyleClass().add("header-row");

		getChildren().add(headerRow);

		// Create rows for each search result
		for (Inventar inventar : searchController.getSearchResults()) {
			HBox row = new HBox();
			row.getChildren().addAll(
				createStyledLabel(inventar.getBeskrivelse()),
				createStyledLabel(inventar.getInnkjøpsPris() + " kr"),
				createStyledLabel(inventar.getInnkjøpsDato().toString()),
				createActionButtons(inventar)
			);
			row.setSpacing(10);
			row.setPadding(new javafx.geometry.Insets(10));
			row.getStyleClass().add("data-row");
			getChildren().add(row);
		}
	}

	// Method to create styled labels
	private Label createStyledLabel(String text) {
		Label label = new Label(text);
		label.getStyleClass().add("data-label");
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

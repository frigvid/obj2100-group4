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
/**
 * Denne klassen representerer visningskomponenten for søkeresultater i klientapplikasjonen.
 * Denne visningen inkluderer funksjonalitet for å vise søkeresultater i en tabellform med mulighet for sortering
 * og handlinger som visning, redigering og sletting av hvert inventarobjekt.
 */
public class SearchResultView extends VBox {
	private SearchController searchController;
	private javafx.collections.ObservableList<Inventar> searchResults;
	private boolean sortAscendingType = true;
	private boolean sortAscendingKategori = true;
	private boolean sortAscendingBeskrivelse = true;
	private boolean sortAscendingInnkjøpsPris = true;
	private boolean sortAscendingInnkjøpsDato = true;

	/**
	 * Konstruerer en ny visning for søkeresultater med en tilhørende søkekontroller.
	 *
	 * @param searchController Kontrolleren som håndterer søkeoperasjoner og data.
	 */
	public SearchResultView(SearchController searchController) {
		this.searchController = searchController;
		initSearchResultView();
	}
	/**
	 * Initialiserer visningsoppsettet for søkeresultater. Denne metoden laster relevant CSS,
	 * setter opp en GridPane for å vise søkeresultater og initialiserer lyttere for sortering.
	 */
	private void initSearchResultView() {
		// Load the CSS file
		getStylesheets().add("/style.css");

		if(searchController.getSearchResults() == null || searchController.getSearchResults().isEmpty()){
			Label empty = new Label("Ingen resultat for ditt søk!");
			getChildren().add(empty);
			return;
		}

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

		kategoriHeader.setStyle("-fx-text-alignment: center; -");
		kategoriHeader.setOnMouseClicked(event -> sortResults("header"));

		beskrivelseHeader.setStyle("-fx-text-alignment: center;");
		beskrivelseHeader.setOnMouseClicked(event -> sortResults("beskrivelse"));
		beskrivelseHeader.setMaxWidth(200);

		innkjøpsPrisHeader.setStyle("-fx-text-alignment: center;");
		innkjøpsPrisHeader.setOnMouseClicked(event -> sortResults("innkjøpsPris"));

		innkjøpsDatoHeader.setStyle("-fx-text-alignment: center;");
		innkjøpsDatoHeader.setOnMouseClicked(event -> sortResults("innkjøpsDato"));

		// Add header labels to the grid pane
		//gridPane.add(typeHeader, 0, 0);
		//gridPane.add(kategoriHeader, 1, 0);
		gridPane.add(beskrivelseHeader, 0, 0);
		gridPane.add(innkjøpsPrisHeader, 1, 0);
		gridPane.add(innkjøpsDatoHeader, 2, 0);
		gridPane.add(actionsHeader, 3, 0);

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
	/**
	 * Viser søkeresultatene i GridPane basert på søkedataene hentet fra søkekontrolleren.
	 * Denne metoden formatterer hver rad i søkeresultatene og inkluderer interaktive handlinger for hvert element.
	 *
	 * @param gridPane GridPane der søkeresultatene vil bli vist.
	 */
	private void displayResults(GridPane gridPane) {
		gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);

		int rowIndex = 1;
		for (Inventar inventar : searchResults) {
			//Label typeLabel = createStyledLabel(inventar.getType());
			// FIXME: Temporary removal after merging.
			//Label kategoriLabel = createStyledLabel(inventar.getKategori().getKategoriNavn());
			Label beskrivelseLabel = createStyledLabel(inventar.getBeskrivelse());
			Label innkjøpsPrisLabel = createStyledLabel(inventar.getInnkjopspris() + " kr");
			Label innkjøpsDatoLabel = createStyledLabel(inventar.getInnkjopsdato() + "");
			HBox actionsBox = createActionButtons(inventar);

			// Add the data labels and actions box to the grid pane
			//gridPane.add(typeLabel, 0, rowIndex);
			// FIXME: Temporary removal after merging.
			//gridPane.add(kategoriLabel, 1, rowIndex);
			gridPane.add(beskrivelseLabel, 0, rowIndex);
			gridPane.add(innkjøpsPrisLabel, 1, rowIndex);
			gridPane.add(innkjøpsDatoLabel, 2, rowIndex);
			gridPane.add(actionsBox, 3, rowIndex);

			// Style the data row
			//typeLabel.getStyleClass().add("data-label");
			// FIXME: Temporary removal after merging.
			//kategoriLabel.getStyleClass().add("data-label");



			rowIndex++;
		}
	}
	/**
	 * Sorterer søkeresultatene basert på spesifiserte kolonnekriterier.
	 * Sorteringen endrer rekkefølgen fra stigende til synkende ved gjentatte klikk på samme kolonne.
	 *
	 * @param column Kolonnen som søkeresultatene skal sorteres etter.
	 */
	private void sortResults(String column) {
		Comparator<Inventar> comparator = null;
		boolean sortAscending = true;

		switch (column) {
			case "type":
				// FIXME: Temporary removal after merging.
				//comparator = Comparator.comparing(Inventar::getType);
				sortAscending = sortAscendingType;
				sortAscendingType = !sortAscendingType;
				break;
			case "kategori":
				// FIXME: Temporary removal after merging.
				//comparator = Comparator.comparing(inventar -> inventar.getKategori().getKategoriNavn());
				sortAscending = sortAscendingKategori;
				sortAscendingKategori = !sortAscendingKategori;
				break;
			case "beskrivelse":
				comparator = Comparator.comparing(Inventar::getBeskrivelse);
				sortAscending = sortAscendingBeskrivelse;
				sortAscendingBeskrivelse = !sortAscendingBeskrivelse;
				break;
			case "innkjøpsPris":
				comparator = Comparator.comparing(Inventar::getInnkjopspris);
				sortAscending = sortAscendingInnkjøpsPris;
				sortAscendingInnkjøpsPris = !sortAscendingInnkjøpsPris;
				break;
			case "innkjøpsDato":
				comparator = Comparator.comparing(Inventar::getInnkjopsdato);
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



	/**
	 * Oppretter og returnerer en stilisert Label basert på inngitt tekst.
	 * Denne metoden setter også passende CSS-stiler for teksten.
	 *
	 * @param text Teksten som skal vises i labelen.
	 * @return En stilisert Label med teksten.
	 */
	private Label createStyledLabel(String text) {
		Label label = new Label(text);
		return label;
	}
	/**
	 * Oppretter og returnerer en Label med stilisert tekst for kolonneoverskrifter.
	 *
	 * @param text Teksten som skal vises i overskriftslabelen.
	 * @return En Label tilpasset som kolonneoverskrift.
	 */
	private Label createStyledLabelText(String text) {
		Label label = new Label(text);
		label.setStyle("-fx-text-alignment: center; -fx-font-size: 20px; -fx-font-style: bold");
		return label;
	}

	/**
	 * Oppretter og returnerer en HBox som inneholder handlingsknapper for hver rad i søkeresultatene.
	 * Dette inkluderer knapper for å vise, redigere og slette inventarobjekter.
	 *
	 * @param inventar Inventarobjektet som handlingsknappene gjelder for.
	 * @return En HBox som inneholder handlingsknapper.
	 */
	private HBox createActionButtons(Inventar inventar) {
		Button viewButton = new Button("View");
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");

		// Add CSS class to buttons
		viewButton.getStyleClass().add("action-button");
		editButton.getStyleClass().add("action-button");
		deleteButton.getStyleClass().add("delete-button");

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
		searchController.getClientView().setViewInventarTabContent(inventar);
		System.out.println("Viewing: " + inventar.getBeskrivelse());
	}

	private void handleEditAction(Inventar inventar) {
		searchController.getClientView().setEditInventarTabContent(inventar);
		System.out.println("Editing: " + inventar.getBeskrivelse());
	}

	private void handleDeleteAction(Inventar inventar) {
		// Implement the delete action
		searchController.getClientView().setDeleteInventarTabContent(inventar);
		System.out.println("Deleting: " + inventar.getBeskrivelse());
	}


}

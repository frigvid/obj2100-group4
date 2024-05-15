package usn.obj2100;

import javafx.scene.layout.BorderPane;
import usn.obj2100.Search.SearchBarView;
import usn.obj2100.Search.SearchController;

/**
 * ClientController er en klasse som fungerer som kontroller for klient, den kaller på andre kontrollere og den initierer views med handlers

 * @return void
 */
public class ClientController {

	private ClientView clientView;
	private SearchBarView searchBarView;
	private InventarSearch inventarSearch;
	private DatabaseManager dbManager;
	private SearchController searchController;

	/**
	 * Konstruktør for ClientController

	 * @return void
	 */
	ClientController(BorderPane root) {
		this.dbManager = new DatabaseManager();
		this.clientView = new ClientView(root, dbManager, this); // Her initialiserer vi Controller med BorderPane
		this.searchController = new SearchController(clientView);
	}


	public ClientView getClientView() {
		return clientView;
	}

	public SearchController getSearchController() {
		return searchController;
	}
}

package usn.obj2100;

import javafx.scene.layout.BorderPane;
import usn.obj2100.Search.SearchBarView;
import usn.obj2100.Search.SearchController;

/**
 * ClientController er en klasse som fungerer som kontroller for klient, den kaller på andre kontrollere og den initierer views med handlers
 * @autor Borgar Flaen Stensrud

 * @return void
 */
public class ClientController {
	private BorderPane root;
	private ClientView clientView;
	private SearchBarView searchBarView;
	private InventarSearch inventarSearch;
	private DatabaseManager dbManager;
	private SearchController searchController;

	/**
	 * Konstruktør for ClientController
	 * @param root
	 * @return void
	 * @autor Borgar Flaen Stensrud
	 */
	ClientController( BorderPane root ) {
		this.root = root;
		this.dbManager = new DatabaseManager();
		this.clientView = new ClientView(root, dbManager); // Her initialiserer vi Controller med BorderPane
		this.searchController = new SearchController(clientView);
	}




	public SearchController getSearchController() {
		return searchController;
	}
}

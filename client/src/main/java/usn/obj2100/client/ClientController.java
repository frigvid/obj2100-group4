package usn.obj2100.client;

import javafx.scene.layout.BorderPane;
import usn.obj2100.client.GUI.StartView;
import usn.obj2100.client.Inventar.InventarController;
import usn.obj2100.client.Search.SearchBarView;
import usn.obj2100.client.Search.SearchController;
import usn.obj2100.client.Search.SearchHandlers;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Inventar;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientController er en klasse som fungerer som kontroller for klient, den kaller på andre kontrollere og den initierer views med handlers

 * @return void
 */
public class ClientController {

	private ClientView clientView;
	private SearchBarView searchBarView;
	private SearchController searchController;
	private Client serverConnection;
	private ArrayList<Inventar>  allInvenatar;
	private Inventar selectedInventar;
	private InventarController inventarController;
	private ScreenController screen;
	private SearchBarView searchBar;
	private SearchHandlers searchHandlers;
	final private BorderPane root;
	/**
	 * Konstruktør for ClientController

	 * @return void
	 */
	ClientController(BorderPane root, Client serverConnection) {
		this.root = root;
		this.serverConnection = serverConnection;
		this.searchController = new SearchController(this);
		this.screen = new ScreenController(this);
		this.clientView = new ClientView(this);
		this.inventarController = new InventarController(this);
	}

	public void initHandlers(){
		this.searchHandlers = new SearchHandlers(this, clientView);
	}


	public ClientView getClientView() {
		return clientView;
	}

	public SearchController getSearchController() {
		return searchController;
	}
	
	public Client getServerConnection(){
		return serverConnection;
	}
	
	public void setSelectedInvetar(Inventar inventar){
		selectedInventar = inventar;
	}
	
	public Inventar getSelectedInvetar(){
		return selectedInventar;
	}
	
	public InventarController getInventarController(){
		return inventarController;
	}

	public ScreenController getScreen() {
		return screen;
	}

	public BorderPane getRoot(){
		return root;
	}
}

package usn.obj2100.client;

import javafx.scene.layout.BorderPane;
import usn.obj2100.client.Search.SearchBarView;
import usn.obj2100.client.Search.SearchController;
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
	/**
	 * Konstruktør for ClientController

	 * @return void
	 */
	ClientController(BorderPane root, Client serverConnection) {
		this.serverConnection = serverConnection;
		this.clientView = new ClientView(root, this);
		this.searchController = new SearchController(this);
		initStartData();
	}
	
	private void initStartData()
	{
		List<Inventar> inventarList = (List<Inventar>) serverConnection.request(Type.INVENTAR);;
		
		if (inventarList != null)
		{
			for (Inventar inventar: inventarList)
			{
				System.out.println(inventar);
			}
		}
		else
		{
			System.out.println("The list is empty or null.");
		}
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
}

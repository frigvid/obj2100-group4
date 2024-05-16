package usn.obj2100.client.Search;

import javafx.scene.layout.VBox;
import usn.obj2100.client.Client;
import usn.obj2100.client.ClientController;
import usn.obj2100.client.ClientView;
import usn.obj2100.client.Help.HelpView;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Inventar;
import usn.obj2100.shared.model.InventarExtended;
import usn.obj2100.shared.model.Kategori;
import usn.obj2100.shared.model.Search;

import java.util.ArrayList;
import java.util.List;
/**
 * Controls the search functionalities within the client application.
 * This class manages all interactions related to searching for inventories, including initializing data,
 * handling user search inputs, and retrieving search results from the server.
 *
 * It integrates with several other components such as {@link SearchBarView} and {@link HelpView} to provide a comprehensive user experience.
 */
public class SearchController {
	private Search.Builder search;
	private SearchBarView searchView;
	private ClientView clientView;
	SearchHandlers searchHandlers;
	private List<InventarExtended> searchResults;
	private ClientController clientController;
	private Client con;
	private HelpView helper;
	/**
	 * Initializes a new instance of the SearchController with a reference to the ClientController.
	 * This constructor sets up the necessary data and interfaces required for the search operations.
	 *
	 * @param clientController The controller that manages client-wide operations and data.
	 */
	public SearchController( ClientController clientController ) {
		this.clientController = clientController;
		this.con = clientController.getServerConnection();
		this.search = new Search.Builder();
		this.clientView = clientController.getClientView();
		this.helper = new HelpView();
		initData();
	}
	/**
	 * Initializes data required for the search functionality.
	 * This method pre-loads some inventories to display initially or sets up any necessary state before the user begins a search.
	 */
	private void initData() {
		setViewAllInventar();
	}
	/**
	 * Sets the search mode based on a given search string.
	 * This function configures the search criteria based on user input and retrieves the matching inventory items from the server.
	 *
	 * @param searchString The description to be used for searching inventories.
	 */
	public void setSearchMode(String seachString){
		Search.Builder search1 = new Search.Builder();
		search1.searchByBeskrivelse(seachString);

		Search query = new Search.Builder()
			.searchByBeskrivelse(seachString)
			.build();

		try
		{
			List<InventarExtended> test = (List<InventarExtended>) clientController.getServerConnection().request(query);
			searchResults = test;
		}
		catch (Exception error)
		{
			System.out.println(System.err);
		}

			/*try
		{
			@SuppressWarnings("unchecked")  // This annotation suppresses unchecked casting warnings
			List<Object> obj =  (List<Object>)  clientController.getServerConnection().request(search1.build());
			System.out.println(obj);
			//List<Object> invs = (List<Object>) obj;
			List<Inventar> inventarList = new ArrayList<>();
			for(Object inv: obj){
				Inventar inventar = (Inventar) inv;
				inventarList.add(inventar);
				System.out.println("Search: " + seachString + " " + inv);
			}
			searchResults = inventarList;

		} catch (Exception e){
			System.out.println("Search does not return init data!");
				e.fillInStackTrace();
			System.out.println(e.toString());
		}*/
	}
	/**
	 * Loads all inventory items to be displayed initially or when a specific filter is not applied.
	 * This method requests the full list of inventories from the server.
	 */
	public void setViewAllInventar(){
		Object newSearchResults =  clientController.getServerConnection().request(Type.INVENTAR);
		try
		{
			@SuppressWarnings("unchecked")  // This annotation suppresses unchecked casting warnings
			List<InventarExtended> inventarList = (List<InventarExtended>) newSearchResults;
			searchResults = inventarList;
		} catch (Exception e){
			System.out.println("Search does not return init data!");
		}
	}
	/**
	 * Returns the list of inventories that match the current search criteria.
	 *
	 * @return A list of {@link Inventar} objects that match the search results.
	 */
	public List<InventarExtended> getSearchResults() {
		return searchResults;
	}

	/**
	 * Placeholder for the actual search functionality that queries the database through a server call.
	 */
	public void search() {
		// Søk i databasen via kall til server!
	}
	/**
	 * Retrieves the current search configuration.
	 *
	 * @return The current search criteria as a {@link Search.Builder}.
	 */
	public Search.Builder getSearch() {
		return search;
	}
	/**
	 * Returns the SearchBarView associated with this controller.
	 *
	 * @return The {@link SearchBarView} managed by this controller.
	 */
	public SearchBarView getSearchView() {
		return searchView;
	}
	/**
	 * Returns the ClientView associated with this controller.
	 *
	 * @return The main {@link ClientView} of the application.
	 */
	public ClientView getClientView() {
		return clientView;
	}
	/**
	 * Updates the search criteria.
	 *
	 * @param search A {@link Search.Builder} that defines the new search criteria.
	 */
	public void setSearch(Search.Builder search) {
		this.search = search;
	}
	/**
	 * Returns the helper view which may provide additional guidance or user assistance related to search.
	 *
	 * @return A {@link VBox} containing help components.
	 */
	public VBox getHelper(){
		return helper;
	}
}

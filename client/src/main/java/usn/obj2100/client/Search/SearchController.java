package usn.obj2100.client.Search;

import javafx.scene.layout.VBox;
import usn.obj2100.client.Client;
import usn.obj2100.client.ClientController;
import usn.obj2100.client.ClientView;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Inventar;
import usn.obj2100.shared.model.Kategori;
import usn.obj2100.shared.model.Search;

import java.util.List;

public class SearchController {

	private Search search;
	private SearchBarView searchView;
	private ClientView clientView;
	SearchHandlers searchHandlers;
	private List<Inventar> searchResults;
	private ClientController clientController;
	private Client con;
	public SearchController( ClientController clientController ) {
		this.clientController = clientController;
		this.con = clientController.getServerConnection();
		this.search = new Search();
		this.clientView = clientController.getClientView();
		this.searchView = new SearchBarView( this);
		this.searchHandlers = new SearchHandlers(this);
		initData();
		
	}

	public void initData() {
	
		Object newSearchResults =  clientController.getServerConnection().request(Type.INVENTAR);
		try
		{
			@SuppressWarnings("unchecked")  // This annotation suppresses unchecked casting warnings
			List<Inventar> inventarList = (List<Inventar>) newSearchResults;
			searchResults = inventarList;
		} catch (Exception e){
			System.out.println("Search does not return init data!");
		}
			
			
			//Kategori[] kategorier = new Kategori[3];
		// FIXME: Temporary removal after merging.
		//kategorier[0] = new Kategori("Møbler");
		//kategorier[1] = new Kategori("Elektronikk");
		//kategorier[2] = new Kategori("Kjøkkenutstyr");

		//searchResults = new Inventar[8];
		// FIXME: Temporary removal after merging.
		//searchResults[0] = new Inventar("Stol", 1000, "2023-04-30" );
		//searchResults[1] = new Inventar("Bord", 2000, "2023-04-30" );
		//searchResults[2] = new Inventar("Sofa", 3000, "2023-04-30" );
		//searchResults[3] = new Inventar("Lampe", 4000, "2023-04-30" );
		//searchResults[4] = new Inventar("Bokhylle", 5000, "2023-04-30" );
		//searchResults[5] = new Inventar("TV", 6000, "2023-04-30") ;
		//searchResults[6] = new Inventar("Kaffemaskin", 7000,  "2023-04-30" );
		//searchResults[7] = new Inventar("Kjøleskap", 8000, "2023-04-30" );
	}


	public List<Inventar> getSearchResults() {
		return searchResults;
	}

	public VBox getSearchResultsView() {
		return new SearchResultView(this);
	}

	public void search() {
		// Søk i databasen via kall til server!
	}
	public Search getSearch() {
		return search;
	}
	public SearchBarView getSearchView() {
		return searchView;
	}
	public ClientView getClientView() {
		return clientView;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public void setSearchView(SearchBarView searchView) {
		this.searchView = searchView;
	}
}

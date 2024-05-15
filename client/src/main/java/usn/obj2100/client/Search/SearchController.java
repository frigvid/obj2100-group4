package usn.obj2100.client.Search;

import javafx.scene.layout.VBox;
import usn.obj2100.client.Client;
import usn.obj2100.client.ClientController;
import usn.obj2100.client.ClientView;
import usn.obj2100.client.Help.HelpView;
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
	private HelpView helper;
	public SearchController( ClientController clientController ) {
		this.clientController = clientController;
		this.con = clientController.getServerConnection();
		this.search = new Search();
		this.clientView = clientController.getClientView();
		this.searchView = new SearchBarView( clientController);
		this.searchHandlers = new SearchHandlers(this);
		this.helper = new HelpView();
		initData();
		
	}

	private void initData() {
		setViewAllInventar();
	}

	public void setViewAllInventar(){
		Object newSearchResults =  clientController.getServerConnection().request(Type.INVENTAR);
		try
		{
			@SuppressWarnings("unchecked")  // This annotation suppresses unchecked casting warnings
			List<Inventar> inventarList = (List<Inventar>) newSearchResults;
			searchResults = inventarList;
		} catch (Exception e){
			System.out.println("Search does not return init data!");
		}
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

	public VBox getHelper(){
		return helper;
	}
}

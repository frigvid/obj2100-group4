package usn.obj2100.client.Search;

import javafx.scene.layout.VBox;
import usn.obj2100.client.ClientView;
import usn.obj2100.shared.model.Inventar;
import usn.obj2100.shared.model.Search;

public class SearchController {

	private Search search;
	private SearchBarView searchView;
	private ClientView clientView;
	SearchHandlers searchHandlers;
	private Inventar[] searchResults;

	public SearchController( ClientView clientView ) {
		this.search = new Search();
		this.clientView = clientView;
		this.searchView = new SearchBarView( this);
		this.searchHandlers = new SearchHandlers(this);
		initDummyData();
	}

	public void initDummyData() {
		Kategori[] kategorier = new Kategori[3];
		kategorier[0] = new Kategori("Møbler");
		kategorier[1] = new Kategori("Elektronikk");
		kategorier[2] = new Kategori("Kjøkkenutstyr");

		searchResults = new Inventar[8];
		//searchResults[0] = new Inventar("Stol", 1000, "2023-04-30" );
		//searchResults[1] = new Inventar("Bord", 2000, "2023-04-30" );
		//searchResults[2] = new Inventar("Sofa", 3000, "2023-04-30" );
		//searchResults[3] = new Inventar("Lampe", 4000, "2023-04-30" );
		//searchResults[4] = new Inventar("Bokhylle", 5000, "2023-04-30" );
		//searchResults[5] = new Inventar("TV", 6000, "2023-04-30") ;
		//searchResults[6] = new Inventar("Kaffemaskin", 7000,  "2023-04-30" );
		//searchResults[7] = new Inventar("Kjøleskap", 8000, "2023-04-30" );
	}


	public Inventar[] getSearchResults() {
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

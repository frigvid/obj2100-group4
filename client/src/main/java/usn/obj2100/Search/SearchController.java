package usn.obj2100.Search;

import javafx.scene.layout.VBox;
import usn.obj2100.ClientView;
import usn.obj2100.Inventar.Inventar;
import usn.obj2100.Inventar.Kategori;

import java.util.Date;

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
		searchResults[0] = new Inventar("Stol", kategorier[0] , 1000, "2023-04-30" );
		searchResults[1] = new Inventar("Bord",kategorier[0], 2000, "2023-04-30" );
		searchResults[2] = new Inventar("Sofa", kategorier[0], 3000, "2023-04-30" );
		searchResults[3] = new Inventar("Lampe", kategorier[1],4000, "2023-04-30" );
		searchResults[4] = new Inventar("Bokhylle", kategorier[0], 5000, "2023-04-30" );
		searchResults[5] = new Inventar("TV",  kategorier[1], 6000, "2023-04-30") ;
		searchResults[6] = new Inventar("Kaffemaskin",kategorier[2], 7000,  "2023-04-30" );
		searchResults[7] = new Inventar("Kjøleskap", kategorier[2],8000, "2023-04-30" );
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

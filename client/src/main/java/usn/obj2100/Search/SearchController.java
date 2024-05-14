package usn.obj2100.Search;

import usn.obj2100.ClientView;

public class SearchController {

	private Search search;
	private SearchBarView searchView;
	private ClientView clientView;
	SearchHandlers searchHandlers;

	public SearchController( ClientView clientView ) {
		this.search = new Search();
		this.clientView = clientView;
		this.searchView = new SearchBarView( this);
		this.searchHandlers = new SearchHandlers(this);
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

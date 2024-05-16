package usn.obj2100.client;

import usn.obj2100.client.GUI.StartView;
import usn.obj2100.client.Inventar.NewInventarView;
import usn.obj2100.client.Inventar.Views.DeleteInventarView;
import usn.obj2100.client.Inventar.Views.EditInventarView;
import usn.obj2100.client.Search.SearchBarView;
import usn.obj2100.client.Search.SearchResultView;

public class ScreenController {
	private StartView startScreen;
	final private NewInventarView newInventarScreen;
	final private ClientController mc;
	private SearchBarView searchBar;
	private SearchResultView searchResultScreen;
	private EditInventarView editInventarScreen;
	private DeleteInventarView deleteInvetarScreen;


	public ScreenController(ClientController mc){
		this.mc = mc;
		this.newInventarScreen = new NewInventarView(mc);
	}

	public DeleteInventarView getDeleteInvetarScreen() {
		if(mc.getSelectedInvetar() != null){
			this.deleteInvetarScreen = new DeleteInventarView(mc);
			return deleteInvetarScreen;
		}
		return null;
	}

	public EditInventarView getEditInventarScreen(){
		if(mc.getSelectedInvetar() != null){
		editInventarScreen = new EditInventarView(mc);
		return editInventarScreen;
		}
		return null;
	}

	public SearchResultView getSearchResultScreen(String search){
			mc.getSearchController().setSearchMode(search);
			searchResultScreen = new SearchResultView(mc.getSearchController());
			return searchResultScreen;
	}

	public NewInventarView getNewInventarScreen(){
		return newInventarScreen;
	}

	public StartView getStartScreen() {
		return startScreen;
	}

	public SearchResultView getViewAllScreen(){
		mc.getSearchController().setViewAllInventar();
		searchResultScreen = new SearchResultView(mc.getSearchController());
		return searchResultScreen;
	}
	public void setStartScreen(StartView startScreen){
		this.startScreen = startScreen;
	}
	public void setSearchBar(SearchBarView searchBar){
		this.searchBar = searchBar;
	}
}

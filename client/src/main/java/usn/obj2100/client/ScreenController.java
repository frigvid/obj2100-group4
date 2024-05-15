package usn.obj2100.client;

import usn.obj2100.client.GUI.StartView;
import usn.obj2100.client.Inventar.NewInventarView;
import usn.obj2100.client.Inventar.Views.DeleteInventarView;
import usn.obj2100.client.Inventar.Views.EditInventarView;
import usn.obj2100.client.Search.SearchResultView;

public class ScreenController {
	final private StartView startScreen;
	final private NewInventarView newInventarScreen;
	final private ClientController mc;
	private SearchResultView searchResultScreen;
	private EditInventarView editInventarScreen;
	private DeleteInventarView deleteInvetarScreen;


	public ScreenController(ClientController mc){
		this.mc = mc;
		this.startScreen = new StartView(mc);
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

	public SearchResultView getSearchResultScreen(){
		if(mc.getSearchController().getSearchResults() != null){
			searchResultScreen = new SearchResultView(mc.getSearchController());
			return searchResultScreen;
		}
		return null;
	}

	public NewInventarView getNewInventarScreen(){
		return newInventarScreen;
	}

	public StartView getStartScreen() {
		return startScreen;
	}

	public SearchResultView getViewAllScreen(){
		mc.getSearchController().setViewAllInventar();
		return searchResultScreen;
	}
}

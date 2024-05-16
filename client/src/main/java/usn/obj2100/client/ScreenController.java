package usn.obj2100.client;

import usn.obj2100.client.GUI.StartView;
import usn.obj2100.client.Inventar.NewInventarView;
import usn.obj2100.client.Inventar.Views.DeleteInventarView;
import usn.obj2100.client.Inventar.Views.EditInventarView;
import usn.obj2100.client.Search.SearchBarView;
import usn.obj2100.client.Search.SearchResultView;
import usn.obj2100.client.Search.SearchResultViewAll;

/**
 * ScreenController klassen håndterer visningen av forskjellige skjermer eller visninger i applikasjonen.
 * Denne klassen er ansvarlig for å opprette og vise korrekte visninger basert på brukerhandlinger eller andre systemhendelser.
 */
public class ScreenController {
	private StartView startScreen; // Startvisningen som vises når applikasjonen starter.
	private final NewInventarView newInventarScreen; // Visning for opprettelse av nytt inventar.
	private final ClientController mc; // Sentral kontroller som styrer logikken.
	private SearchBarView searchBar; // Søkefelt og relaterte visninger.
	private SearchResultView searchResultScreen; // Visning for søkeresultater.
	private SearchResultViewAll searchResultScreenAll;
	private EditInventarView editInventarScreen; // Redigeringsvisning for inventar.
	private DeleteInventarView deleteInvetarScreen; // Slettevisning for inventar.

	/**
	 * Konstruktør for ScreenController.
	 *
	 * @param mc Hovedkontrolleren som ScreenController avhenger av for å utføre sin logikk.
	 */
	public ScreenController(ClientController mc){
		this.mc = mc;
		this.newInventarScreen = new NewInventarView(mc);
	}
	/**
	 * Henter visningen for sletting av inventar, oppretter den hvis nødvendig.
	 *
	 * @return Visningen for sletting av inventar, eller null hvis ingen inventar er valgt.
	 */
	public DeleteInventarView getDeleteInvetarScreen() {
		if(mc.getSelectedInvetar() != null){
			this.deleteInvetarScreen = new DeleteInventarView(mc);
			return deleteInvetarScreen;
		}
		return null;
	}
	/**
	 * Henter redigeringsvisningen for inventar, oppretter den hvis nødvendig.
	 *
	 * @return Redigeringsvisningen for inventar, eller null hvis ingen inventar er valgt.
	 */
	public EditInventarView getEditInventarScreen(){
		if(mc.getSelectedInvetar() != null){
		editInventarScreen = new EditInventarView(mc);
		return editInventarScreen;
		}
		return null;
	}
	/**
	 * Oppretter og returnerer en visning for søkeresultater basert på en søkestreng.
	 *
	 * @param search Søkestrengen som brukes for å utføre søket.
	 * @return Visningen for søkeresultater.
	 */
	public SearchResultView getSearchResultScreen(String search){
			mc.getSearchController().setSearchMode(search);
			searchResultScreen = new SearchResultView(mc.getSearchController());
			return searchResultScreen;
	}
	/**
	 * Henter visningen for opprettelse av nytt inventar.
	 *
	 * @return Visningen for nytt inventar.
	 */
	public NewInventarView getNewInventarScreen(){
		return newInventarScreen;
	}
	/**
	 * Henter startvisningen.
	 *
	 * @return Startvisningen.
	 */
	public StartView getStartScreen() {
		return startScreen;
	}
	/**
	 * Setter søkeresultat visning med alle inventar
	 */
	public SearchResultViewAll getViewAllScreen(){
		mc.getSearchController().setViewAllInventar();
		searchResultScreenAll = new SearchResultViewAll(mc.getSearchController());
		return searchResultScreenAll;
	}
	/**
	 * Setter startvisningen.
	 *
	 * @param startScreen Visningen som skal settes som startvisning.
	 */
	public void setStartScreen(StartView startScreen){
		this.startScreen = startScreen;
	}
	/**
	 * Setter søkebaren som skal brukes i brukergrensesnittet.
	 *
	 * @param searchBar Søkebaren som skal settes.
	 */
	public void setSearchBar(SearchBarView searchBar){
		this.searchBar = searchBar;
	}
}

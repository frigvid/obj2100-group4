package usn.obj2100.client;

import javafx.scene.layout.BorderPane;
import usn.obj2100.client.GUI.StartView;
import usn.obj2100.client.Inventar.InventarController;
import usn.obj2100.client.Search.SearchBarView;
import usn.obj2100.client.Search.SearchController;
import usn.obj2100.client.Search.SearchHandlers;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Inventar;
import usn.obj2100.shared.model.InventarExtended;

import java.util.ArrayList;
import java.util.List;
/**
 * Klassen ClientController fungerer som hovedkontrolleren for klientapplikasjonen.
 * Den koordinerer og håndterer interaksjoner mellom brukergrensesnittet og den underliggende forretningslogikken,
 * og fungerer som bindeleddet mellom ulike visninger og deres respektive kontrollere.
 */
public class ClientController {

	private ClientView clientView;
	private SearchBarView searchBarView;
	private SearchController searchController;
	private Client serverConnection;
	private ArrayList<Inventar>  allInvenatar;
	private InventarExtended selectedInventar;
	private InventarController inventarController;
	private ScreenController screen;
	private SearchBarView searchBar;
	private SearchHandlers searchHandlers;
	final private BorderPane root;
	/**
	 * Konstruktør som initialiserer ClientController med nødvendige avhengigheter.
	 *
	 * @param root Hovedpanelet som tjener som rotcontainer for brukergrensesnittet i applikasjonen.
	 * @param serverConnection Referansen til nettverksklienten som håndterer kommunikasjon med serveren.
	 */
	ClientController(BorderPane root, Client serverConnection) {
		this.root = root;
		this.serverConnection = serverConnection;
		this.searchController = new SearchController(this);
		this.screen = new ScreenController(this);
		this.clientView = new ClientView(this);
		this.inventarController = new InventarController(this);
	}
	/**
	 * Initialiserer hendelseshåndterere for å støtte interaktivitet i de ulike brukergrensesnittkomponentene.
	 */
	public void initHandlers(){
		this.searchHandlers = new SearchHandlers(this, clientView);
	}

	/**
	 * Henter hovedvisningen til klientapplikasjonen.
	 *
	 * @return Hovedvisningen som er tilknyttet denne kontrolleren.
	 */
	public ClientView getClientView() {
		return clientView;
	}
	/**
	 * Henter søkekontrolleren som håndterer all søkelogikk og interaksjon i applikasjonen.
	 *
	 * @return Søkekontrolleren tilknyttet denne klientkontrolleren.
	 */
	public SearchController getSearchController() {
		return searchController;
	}
	/**
	 * Henter nettverksklienten som håndterer all kommunikasjon mellom klienten og serveren.
	 *
	 * @return Klientobjektet som håndterer serverkommunikasjon.
	 */
	public Client getServerConnection(){
		return serverConnection;
	}
	/**
	 * Setter det valgte inventarobjektet som er valgt i brukergrensesnittet.
	 *
	 * @param inventar Det inventarobjektet som skal settes som valgt.
	 */
	public void setSelectedInvetar(InventarExtended inventar){
		selectedInventar = inventar;
	}
	/**
	 * Henter det valgte inventarobjektet.
	 *
	 * @return Det inventarobjektet som er valgt.
	 */
	public InventarExtended getSelectedInvetar(){
		return selectedInventar;
	}
	/**
	 * Henter kontrolleren for inventar, som håndterer logikken knyttet til behandling av inventar.
	 *
	 * @return Kontrolleren som håndterer inventar.
	 */
	public InventarController getInventarController(){
		return inventarController;
	}
	/**
	 * Henter skjermkontrolleren som håndterer bytting og oppdatering av ulike skjermer i applikasjonen.
	 *
	 * @return Skjermkontrolleren.
	 */
	public ScreenController getScreen() {
		return screen;
	}
	/**
	 * Henter hovedpanelet som er roten av brukergrensesnittet i applikasjonen.
	 *
	 * @return Hovedpanelet.
	 */
	public BorderPane getRoot(){
		return root;
	}
}

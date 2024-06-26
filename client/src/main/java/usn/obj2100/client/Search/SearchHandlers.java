package usn.obj2100.client.Search;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.RangeSlider;
import usn.obj2100.client.ClientController;
import usn.obj2100.client.ClientView;
/**
 * Håndterer alle søkerelaterte hendelser og interaksjoner i klientapplikasjonen.
 * Denne klassen initialiserer og binder hendelseshåndterere til de ulike søkekomponentene i brukergrensesnittet,
 * som knapper, tekstfelt og glidebrytere, for å administrere avansert og grunnleggende søkefunksjonalitet.
 *
 * @param mc Hovedkontrolleren for klienten som koordinerer søk og andre hovedfunksjoner.
 * @param cw Hovedvisningen for klienten hvor søkefeltet og relaterte komponenter vises.
 */
public class SearchHandlers {
	private SearchBarView searchBarView;
	private SearchController searchController;
	private ClientView clientView;
	private ClientController mc;
	private ClientView cw;
	/**
	 * Konstruerer en ny søkehåndterer for gitt klientkontroller og klientvisning.
	 * Denne konstruktøren setter opp nødvendig data og initierer søkehåndtererne.
	 *
	 * @param mc Kontrolleren som håndterer klientlogikk.
	 * @param cw Visningen hvor søkefunksjonaliteten er integrert.
	 */
	public SearchHandlers( ClientController mc, ClientView cw) {
		this.mc = mc;
		this.cw = cw;
		this.clientView = mc.getClientView();
		this.searchController = mc.getSearchController();
		this.searchBarView = cw.getSearchBar();
		initSearchHandlers();
		initAdvancedSearchHandlers();
	}
	/**
	 * Initialiserer hendelseshåndterere for grunnleggende søkefunksjoner.
	 * Kobler hendelser til UI-elementer som toggle-knapp for søkefeltet og søkeknappen.
	 */
	private void initSearchHandlers(){
		searchBarView.getSearchToggleButton().setOnAction(event -> {
			HBox advancedSearchForm = searchBarView.getSearchForm();

			if (!clientView.getFooter().getChildren().contains(searchBarView.getSearchForm())) {

				clientView.getFooter().getChildren().add(searchBarView.getSearchForm());
				searchBarView.animateSearchForm(advancedSearchForm, true);
				clientView.getFooter().getChildren().remove(searchBarView.getSearchToggle());

				clientView.getFooter().addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {

					if (advancedSearchForm != null && !advancedSearchForm.contains(e.getX(), e.getY())) {
						searchBarView.animateSearchForm(advancedSearchForm, false);
						clientView.getFooter().getChildren().remove(advancedSearchForm);
						clientView.getFooter().getChildren().add(searchBarView.getSearchToggle());
					}

				});
			} else {
				searchBarView.animateSearchForm(searchBarView.getSearchForm(), false);
				clientView.getFooter().getChildren().add(searchBarView.getSearchToggle());
			}
		});

		searchBarView.getSearchOptions().setOnAction(event -> {
			HBox advancedSearchForm = searchBarView.buildAdvancedSearchForm();
			advancedSearchForm.setAlignment(Pos.TOP_LEFT);

			if (!clientView.getRoot().getChildren().contains(advancedSearchForm)) {
				clientView.getRoot().setLeft(advancedSearchForm);
				searchBarView.animateDrawer(advancedSearchForm, true);
				clientView.getRoot().addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
					if (!advancedSearchForm.contains(e.getX(), e.getY())) {
						searchBarView.animateDrawer(advancedSearchForm, false);
					}
				});
			} else {
				searchBarView.animateDrawer(advancedSearchForm, false);
			}
		});

		searchBarView.getSearchBarHelpButton().setOnAction(event -> {
			VBox helper = searchBarView.getMc().getSearchController().getHelper();
			helper.setAlignment(Pos.TOP_LEFT);
				clientView.getRoot().setRight(helper);
				searchBarView.animateDrawerRight(helper, true);
				clientView.getRoot().addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
					if (!helper.contains(e.getX(), e.getY())) {
						searchBarView.animateDrawerRight(helper, false);
						clientView.getRoot().setRight(null);
					}
				});
			});


		//TODO Performance: If the action triggered by the text change is heavy (like querying a database), consider debouncing the input or waiting until the user stops typing for a certain duration before executing the action, to avoid performing the action too frequently.
		searchBarView.getSearchField().textProperty().addListener(e -> {
			String newSearch = searchBarView.getSearchField().getText();
			mc.getClientView().setNewTabContent(newSearch);
		});

		searchBarView.getSearchButton().setOnAction(event -> {

		});
	}
	/**
	 * Initialiserer hendelseshåndterere for avansert søk.
	 * Setter opp lyttere for avanserte søkefelter og håndterer dynamisk opprettelse og styring av disse feltene.
	 */
	private void initAdvancedSearchHandlers(){
		for ( SearchField<TextField> field : searchBarView.getAdvancedFieldsText() ) {
			System.out.println(field.getOption());
			addEventHandler(field.getField(), field.getOption());
		}
		for (SearchField<ComboBox<String>> comboBox : searchBarView.getAdvancedFieldsComboBoxString()) {
			addEventHandler(comboBox.getField(), comboBox.getOption());
		}
		for (SearchField<ComboBox<Integer>> comboBox : searchBarView.getAdvancedFieldsComboBoxInt()) {
			addEventHandler(comboBox.getField(), comboBox.getOption());
		}
		for (SearchField<RangeSlider> range : searchBarView.getAdvancedFieldsRange()) {
			addEventHandlerRange(range.getField(), range.getOption());
		}
	}


	/**
	 * Legger til en hendelseslytter for tekstfelt basert på et spesifisert søkealternativ.
	 * Denne metoden lytter til tekstendringer og oppdaterer søkekriteriene i søkekontrolleren.
	 *
	 * @param field     Tekstfeltet som skal overvåkes.
	 * @param fieldType Søkealternativet som spesifiserer hvordan tekstendringene skal håndteres.
	 */
	private <T> void addEventHandler( CheckBox check, SEARCHOPTION field ){
		check.selectedProperty().addListener(( observable, oldValue, newValue ) -> {
			System.out.println(field + " oppdatert: " + newValue);
			switch (field) {
				case IBRUK -> {
					searchController.getSearch().searchByIBruk(newValue);
				}
				case IKKEIBRUK -> {
					searchController.getSearch().searchByIkkeIBruk(newValue);
				}
			}
		});
	}
	/**
	 * Legger til en hendelseslytter for en RangeSlider basert på et gitt søkealternativ.
	 * Denne metoden lytter til endringer i slider-verdier og oppdaterer søkekriteriene tilsvarende.
	 *
	 * @param range Slideren som skal overvåkes.
	 * @param field Søkealternativet som definerer hvordan sliderens verdiendringer skal håndteres.
	 */
	private <T> void addEventHandler( TextField field, SEARCHOPTION fieldType ){
		field.textProperty().addListener(( observableValue, s, t1 ) -> {
			System.out.println(fieldType + " oppdatert: " + s + " " + t1);
			switch (fieldType) {
				case SEARCH -> {
					searchController.getSearch().search(t1);
				}
				case PLASSERING -> {
					searchController.getSearch().searchByPlassering(t1);
				}
				case BESKRIVELSE -> {
					searchController.getSearch().searchByBeskrivelse(t1);
				}
				case INNKJOPSDATO -> {
					// FIXME: Incompatible type.
					//searchController.getSearch().searchByInnkjopsdato(Integer.parseInt(t1));
				}
		}
		});
	}

	// Metode for å legge til en hendelsesbehandler for en RangeSlider basert på et gitt SEARCHOPTION-felt.
	// Metode for å legge til en hendelsesbehandler for en RangeSlider basert på et gitt SEARCHOPTION-felt.
	private <T> void addEventHandlerRange(RangeSlider range, SEARCHOPTION field) {
		// Legger til en hendelsesbehandler for endringer i RangeSliderens verdi.
		range.setOnMouseReleased(event -> {
			// Skriver ut oppdateringsinformasjon til konsollen.
			System.out.println(field + " oppdatert: " + range.getLowValue() + " - " + range.getHighValue());

			// Switch-case for å håndtere forskjellige søkealternativer basert på feltet.
			switch (field) {
				case PRIS -> {
					// Oppdaterer søkekriteriene for prisområdet.
					searchController.getSearch().searchByPris((int) range.getLowValue(), (int) range.getHighValue());
				}
				case ANTALL -> {
					// Oppdaterer søkekriteriene for antallområdet.
					searchController.getSearch().searchByAntall((int) range.getLowValue(), (int) range.getHighValue());
				}
				case LEVETID -> {
					// Oppdaterer søkekriteriene for levetidsområdet.
					searchController.getSearch().searchByLevetid((int) range.getLowValue(), (int) range.getHighValue());
				}
				case FORVENTETKASSERING -> {
					// Oppdaterer søkekriteriene for forventet kassering område.
					searchController.getSearch().searchByForventetKassering((int) range.getLowValue(), (int) range.getHighValue());
				}
				default -> throw new IllegalStateException("Uventet verdi: " + field);
			}
		});
	}

	private <T> void addEventHandler( ComboBox<T> comboBox, SEARCHOPTION field) {
		comboBox.valueProperty().addListener(( observable, oldValue, newValue ) -> {
			System.out.println(field + " oppdatert: " + newValue);
			switch (field) {
				case TYPE -> {
					searchController.getSearch().searchByType(newValue.toString());
				}
				case KATEGORI -> {
					searchController.getSearch().searchByKategori(newValue.toString());
				}
				case TATTUTAVBRUKAARSAK -> {
					searchController.getSearch().searchByTattUtAvBrukÅrsak(newValue.toString());
				}
				case TATTUTAVBRUKAAR -> {
					searchController.getSearch().searchByTattUtAvBrukÅr((int) newValue);
				}

			}
		});
	}

}

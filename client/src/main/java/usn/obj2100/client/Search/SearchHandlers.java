package usn.obj2100.client.Search;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.RangeSlider;
import usn.obj2100.client.ClientView;

public class SearchHandlers {
	private SearchBarView searchBarView;
	private SearchController searchController;
	private ClientView clientView;

	public SearchHandlers( SearchController searchController) {
		this.clientView = searchController.getClientView();
		this.searchController = searchController;
		this.searchBarView = searchController.getSearchView();
		initSearchHandlers();
		initAdvancedSearchHandlers();
	}

	private void initSearchHandlers(){
		searchBarView.getSearchToggleButton().setOnAction(event -> {
			HBox advancedSearchForm = searchBarView.getSearchForm();

			if (!clientView.getMainContent().getChildren().contains(searchBarView.getSearchForm())) {

				clientView.getMainContent().getChildren().add(searchBarView.getSearchForm());
				searchBarView.animateSearchForm(advancedSearchForm, true);
				clientView.getMainContent().getChildren().remove(searchBarView.getSearchToggle());


				searchBarView.getSearchField().textProperty().addListener(new ChangeListener<String>() {
					int count = 0;
					Label searchLabel;
					@Override
					public void changed( ObservableValue<? extends String> observable, String oldValue, String newValue) {
						searchLabel = new Label("Søkeresultater for: " + newValue);

						if(count < 1) {
							clientView.setNewTabContent(searchLabel);
							count++;
						}else{
							clientView.updateTabContent(searchLabel);
						}


						System.out.println("Søkefelt oppdatert: " + newValue);
						// Legg til koden som oppdaterer noe her
					}
				});


				clientView.getMainContent().addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {

					if (advancedSearchForm != null && !advancedSearchForm.contains(e.getX(), e.getY())) {
						searchBarView.animateSearchForm(advancedSearchForm, false);
						clientView.getMainContent().getChildren().remove(advancedSearchForm);
						clientView.getMainContent().getChildren().add(searchBarView.getSearchToggle());
					}

				});
			} else {
				searchBarView.animateSearchForm(searchBarView.getSearchForm(), false);
				clientView.getMainContent().getChildren().add(searchBarView.getSearchToggle());
			}
		});

		searchBarView.getSearchOptions().setOnAction(event -> {
			HBox advancedSearchForm = searchBarView.buildAdvancedSearchForm();
			advancedSearchForm.setAlignment(Pos.TOP_LEFT);

			if (!clientView.getMainContent().getChildren().contains(advancedSearchForm)) {
				clientView.getMainContent().getChildren().add(advancedSearchForm);
				searchBarView.animateDrawer(advancedSearchForm, true);
				clientView.getMainContent().addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
					if (advancedSearchForm != null && !advancedSearchForm.contains(e.getX(), e.getY())) {
						searchBarView.animateDrawer(advancedSearchForm, false);
					}
				});
			} else {
				searchBarView.animateDrawer(advancedSearchForm, false);
			}
		});

		searchBarView.getSearchButton().setOnAction(event -> {

		});
	}

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



	private <T> void addEventHandler( CheckBox check, SEARCHOPTION field ){
		check.selectedProperty().addListener(( observable, oldValue, newValue ) -> {
			System.out.println(field + " oppdatert: " + newValue);
			switch (field) {
				case IBRUK -> {
					searchController.getSearch().setSearchByIBruk(newValue);
				}
				case IKKEIBRUK -> {
					searchController.getSearch().setSearchByIkkeIBruk(newValue);
				}
			}
		});
	}

	private <T> void addEventHandler( TextField field, SEARCHOPTION fieldType ){
		field.textProperty().addListener(( observableValue, s, t1 ) -> {
			System.out.println(fieldType + " oppdatert: " + s + " " + t1);
			switch (fieldType) {
				case SEARCH -> {
					searchController.getSearch().setSearch(t1);
				}
				case PLASSERING -> {
					searchController.getSearch().setSearchByPlassering(t1);
				}
				case BESKRIVELSE -> {
					searchController.getSearch().setSearchByBeskrivelse(t1);
				}
				case INNKJOPSDATO -> {
					searchController.getSearch().setSearchByInnkjopsdato(Integer.parseInt(t1));
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
					searchController.getSearch().setSearchByPris((int) range.getLowValue(), (int) range.getHighValue());
				}
				case ANTALL -> {
					// Oppdaterer søkekriteriene for antallområdet.
					searchController.getSearch().setSearchByAntall((int) range.getLowValue(), (int) range.getHighValue());
				}
				case LEVETID -> {
					// Oppdaterer søkekriteriene for levetidsområdet.
					searchController.getSearch().setSearchByLevetid((int) range.getLowValue(), (int) range.getHighValue());
				}
				case FORVENTETKASSERING -> {
					// Oppdaterer søkekriteriene for forventet kassering område.
					searchController.getSearch().setSearchByForventetKassering((int) range.getLowValue(), (int) range.getHighValue());
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
					searchController.getSearch().setSearchByType(newValue.toString());
				}
				case KATEGORI -> {
					searchController.getSearch().setSearchByKategori(newValue.toString());
				}
				case TATTUTAVBRUKAARSAK -> {
					searchController.getSearch().setSearchByTattUtAvBrukÅrsak(newValue.toString());
				}
				case TATTUTAVBRUKAAR -> {
					searchController.getSearch().setSearchByTattUtAvBrukÅr((int) newValue);
				}

			}
		});
	}

}

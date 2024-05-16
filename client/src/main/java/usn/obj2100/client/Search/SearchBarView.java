package usn.obj2100.client.Search;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.RangeSlider;
import usn.obj2100.client.ClientController;
import usn.obj2100.client.ClientView;


import java.util.ArrayList;
/**
 * Denne klassen representerer søkefeltet for å utføre søk i inventarsystemet.
 * Det tilbyr grunnleggende og avanserte søkealternativer med støtte for dynamisk tilpasning av søkeparametrene
 * basert på brukerens valg.
 * <p>
 * Denne visningen inkluderer et hovedsøkefelt, knapper for å aktivere avansert søk, hjelp for søk, og
 * håndterer animasjoner for visning og skjuling av søkeformularet.
 * <p>
 * Søkeformularet tillater brukere å spesifisere søk basert på forskjellige felter som beskrivelse, plassering,
 * prisklasser, og mange andre kriterier.
 * <p>
 * Eksempel på bruk:
 * <pre>
 * {@code
 * ClientController clientController = new ClientController();
 * ClientView clientView = new ClientView(clientController);
 * SearchBarView searchBarView = new SearchBarView(clientController, clientView);
 * }
 * </pre>
 *
 * @param mc Håndterer logikken for klientkontrolleren som tilrettelegger interaksjon med modellene.
 * @param cw Visningen av klientgrensesnittet som inneholder søkefeltet.
 */
public class SearchBarView  {

	private StackPane footer;
	private DropShadow dropShadow;
	private HBox searchForm = buildSearchForm();
	private HBox searchToggle = searchFormToggle();

	private Button searchToggleButton;
	private Button searchOptions;
	private Button searchButton;
	private Button searchBarHelpButton;

	private TextField searchField;

	private ArrayList<SearchField<TextField>> advancedFieldsText;
	private ArrayList<SearchField<RangeSlider>> advancedFieldsRange;
	private ArrayList<SearchField<ComboBox<String>>> advancedFieldsComboBoxString;
	private ArrayList<SearchField<ComboBox<Integer>>> advancedFieldsComboBoxInt;

	private ClientController mc;

	/**
	 * Initialiserer en ny instans av søkefeltet med koblinger til klientkontrolleren og klientvisningen.
	 * Setter opp UI-komponentene og konfigurerer nødvendige interaksjoner og animasjoner.
	 *
	 * @param mc Klientkontrolleren som koordinerer handlinger mellom brukergrensesnittet og dataene.
	 * @param cw Hovedklientvisningen som inneholder denne søkevisningen.
	 */
	public SearchBarView(ClientController mc, ClientView cw) {
		this.mc = mc;
		this.footer = cw.getFooter();
		this.dropShadow = new DropShadow();
		this.advancedFieldsText = new ArrayList<>();
		this.advancedFieldsRange = new ArrayList<>();
		this.advancedFieldsComboBoxString = new ArrayList<>();
		this.advancedFieldsComboBoxInt = new ArrayList<>();
		init();

	}
	/**
	 * Initialiserer grunnleggende UI-komponenter og legger til nødvendige lyttere og stiler.
	 */
	private void init() {
		dropShadow.setColor(Color.GRAY);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setRadius(5.0);

		this.footer.getChildren().add(searchToggle);
	}

	/**
	 * Oppretter og returnerer hovedsøkeformularet som inkluderer søkefelt og knapper for å håndtere søk.
	 *
	 * @return En HBox som inneholder søkeformularet.
	 */
	private HBox searchFormToggle(){
		HBox toggleContainer = new HBox();

		searchToggleButton = new Button();
		searchToggleButton.setPadding(new Insets(20, 0, 20, 0)); // Setter 20 piksler på toppen og bunnen
		searchToggleButton.getStyleClass().add("search-box-toggle");

		Image image = new Image("search-icon.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(20);  // bredden på bildet
		imageView.setFitHeight(20); // høyden på bildet


		searchToggleButton.setGraphic(imageView);
		searchToggleButton.setEffect(dropShadow);
		searchToggleButton.setAlignment(Pos.CENTER);

		Label searchLabel = new Label("Søk i inventar");
		searchLabel.setAlignment(Pos.CENTER);

		toggleContainer.getChildren().addAll(searchToggleButton, searchLabel);
		toggleContainer.setAlignment(Pos.BOTTOM_RIGHT);
		toggleContainer.setPadding(new Insets(0, 20, 20, 0)); // Setter 20 piksler på høyre side og bunnen
		return toggleContainer;
	}

	/**
	 * Oppretter og returnerer en toggle-knapp for å vise eller skjule det avanserte søkeformularet.
	 *
	 * @return En HBox som inneholder toggle-knappen og dens tilhørende etikett.
	 */
	private HBox buildSearchForm() {
		HBox form = new HBox();
		form.setAlignment(Pos.BOTTOM_CENTER);
		form.getStyleClass().add("search-box");

		searchField = new TextField();
		searchField.getStyleClass().add("text-field-main-search");
		searchField.setPrefWidth(500);
		searchField.setMinWidth(400);
		searchField.setPrefHeight(46);
		searchField.setPromptText("Søk etter inventar...");



		searchButton = new Button();
		searchOptions = new Button("Avansert søk");

		searchOptions.getStyleClass().add("search-options");

		Image image = new Image("search-icon.png");
		ImageView imageView = new ImageView(image);

		imageView.setFitWidth(20);  // bredden på bildet
		imageView.setFitHeight(20); // høyden på bildet

		searchButton.setGraphic(imageView);
		searchButton.getStyleClass().add("search-button");

		Image help = new Image("help-icon.png");
		ImageView helpView = new ImageView(help);
		helpView.setFitWidth(30);
		helpView.setFitHeight(30);
		helpView.getStyleClass().add("help-icon");
		searchBarHelpButton = new Button();
		searchBarHelpButton.setGraphic(helpView);

		boolean show = true;
		searchBarHelpButton.getStyleClass().add("transparent-button");
		searchBarHelpButton.setAlignment(Pos.CENTER_LEFT);
		searchBarHelpButton.setPadding(new Insets(0));

		StackPane searchFieldContainer = new StackPane();
		searchFieldContainer.getChildren().addAll(searchField, searchBarHelpButton);
		StackPane.setAlignment(searchBarHelpButton, Pos.CENTER_LEFT);
		searchBarHelpButton.setTranslateX(10); // Juster denne verdien for å plassere ikonet riktig



		HBox buttonGroup = new HBox();
		buttonGroup.getChildren().addAll(searchFieldContainer, searchOptions, searchButton);
		buttonGroup.setPrefHeight(45);
		buttonGroup.setAlignment(Pos.BOTTOM_CENTER);
		buttonGroup.setEffect(dropShadow); // Legger til skyggeeffekt

		form.getChildren().addAll(buttonGroup);
		form.setSpacing(20);
		form.setPadding(new Insets(20));
		return form;
	}
	/**
	 * Konstruerer og returnerer det avanserte søkeskjemaet, med dynamisk tilpassede søkefelt basert på
	 * tilgjengelige søkekriterier.
	 *
	 * @return En HBox som inneholder det avanserte søkeskjemaet.
	 */
	public HBox buildAdvancedSearchForm() {
		HBox advancedForm = new HBox();
		advancedForm.getStyleClass().add("advanced-search-drawer");
		advancedForm.setAlignment(Pos.TOP_LEFT);
		advancedForm.setLayoutX(0);
		VBox advancedFormContent = new VBox();

		VBox labelsAndFields = initDrawerFields();

		Button advancedButton = new Button("Søk");

		advancedButton.getStyleClass().add("advanced-search-button");
		advancedButton.setAlignment(Pos.BOTTOM_CENTER);
		advancedButton.minWidth(200);
		advancedButton.maxWidth(200);

		VBox advancedSearch = new VBox();
		advancedSearch.setAlignment(Pos.TOP_CENTER);
		advancedSearch.setSpacing(10);

		Label drawerLabel = new Label("Avansert søk");
		drawerLabel.setFont(new javafx.scene.text.Font(28));
		drawerLabel.getStyleClass().add("advanced-search-label");

		advancedFormContent.getChildren().addAll(drawerLabel, labelsAndFields);
		advancedFormContent.setAlignment(Pos.TOP_CENTER);
		advancedFormContent.setSpacing(20);
		advancedFormContent.setPadding(new Insets(10));
		// Legg til dine avanserte søkeelementer her
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(advancedFormContent);
		scrollPane.setMaxWidth(300);
		scrollPane.setMinWidth(300);
		scrollPane.setMaxHeight(550);
		scrollPane.setMinHeight(550);

		advancedSearch.getChildren().addAll(scrollPane, advancedButton);
		VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);

		advancedForm.getChildren().addAll(advancedSearch);


		return advancedForm;
	}

	private VBox initDrawerFields(){
		VBox drawerContent = new VBox();
		drawerContent.setSpacing(10);
		Label advancedLabel = new Label("Avansert søk");
		advancedLabel.setFont(new javafx.scene.text.Font(28));
		advancedLabel.getStyleClass().add("advanced-search-label");


		String[][] labelsAndPrompts = {
			{"Beskrivelse", "Søk på beskrivelse...", SEARCHOPTION.BESKRIVELSE.toString()},
			{"Plassering", "Søk på plassering...", SEARCHOPTION.PLASSERING.toString()},
		};

		SliderData[] labelsAndPromptsSlider = {
			new SliderData("Innkjøps pris", 0, 10000, 100, SEARCHOPTION.PRIS),
			new SliderData("Forventet levetid", 1, 100, 1, SEARCHOPTION.LEVETID),
			new SliderData("Forventet kassering", 1, 50, 1, SEARCHOPTION.FORVENTETKASSERING),
			new SliderData("Antall i inventar", 0, 5000, 5, SEARCHOPTION.ANTALL)
		};

		Label invetaryTypeLabel = new Label("Inventartype");
		invetaryTypeLabel.setFont(new javafx.scene.text.Font(16));
		invetaryTypeLabel.getStyleClass().add("advanced-search-textfield-label");

		ComboBox<String> inventarType = new ComboBox<>();
		inventarType.getItems().addAll("Møbler", "Utsmykning", "Teknisk Utstyr");
		inventarType.setPromptText("Velg inventartype...");
		advancedFieldsComboBoxString.add(new SearchField<>(inventarType, SEARCHOPTION.TYPE));
		drawerContent.getChildren().addAll(invetaryTypeLabel, inventarType);



		ComboBox<String> kategori = new ComboBox<>();
		kategori.getItems().addAll("Møbler - leasing", "Utsmykning - leasing", "Teknisk Utstyr - leasing");
		kategori.setPromptText("Velg kategori...");
		advancedFieldsComboBoxString.add(new SearchField<>(kategori, SEARCHOPTION.KATEGORI));
		drawerContent.getChildren().add(kategori);

		for (String[] pair : labelsAndPrompts) {
			VBox textInputWithLabel = generateTextInputWithLabel(pair[0], pair[1], SEARCHOPTION.valueOf(pair[2]));
			drawerContent.getChildren().add(textInputWithLabel);
		}

		ComboBox<Integer> innkjopAar = new ComboBox<>();
		ComboBox<Integer> utAvBrukPicker = new ComboBox<>();
		int currentYear = java.time.Year.now().getValue();
		for (int year = currentYear - 100; year <= currentYear; year++) {
			innkjopAar.getItems().add(year); // Legger til år fra 100 år tilbake til nåværende år i dropdown
			utAvBrukPicker.getItems().add(year);
		}
		innkjopAar.setPromptText("Velg år...");

		Label yearLabel = new Label("Innkjøpsår");
		yearLabel.setFont(new javafx.scene.text.Font(16));
		advancedFieldsComboBoxInt.add(new SearchField<ComboBox<Integer>>(innkjopAar, SEARCHOPTION.INNKJOPSDATO));
		drawerContent.getChildren().addAll(yearLabel, innkjopAar);



		for (SliderData sliderData : labelsAndPromptsSlider) {
			VBox inputRangeWithLabel = generateInputRangeWithLabel(sliderData.labelText, sliderData.minValue, sliderData.maxValue, sliderData.step, sliderData.searchoption);
			drawerContent.getChildren().add(inputRangeWithLabel);
		}

		CheckBox iBruk = new CheckBox("I bruk");
		CheckBox ikkeIBruk = new CheckBox("Ikke i bruk");

		HBox iBrukContainer = new HBox();

		iBrukContainer.getChildren().addAll(iBruk, ikkeIBruk);
		iBrukContainer.setSpacing(10);

		Label iBrukLabel = new Label("Status");
		iBrukLabel.setFont(new javafx.scene.text.Font(16));
		drawerContent.getChildren().addAll(iBrukLabel, iBrukContainer);

		utAvBrukPicker.setPromptText("Velg år...");
		Label utAvBrukPickerLabel = new Label("Tatt ut av bruk år");
		utAvBrukPickerLabel.setFont(new javafx.scene.text.Font(16));
		advancedFieldsComboBoxInt.add(new SearchField<ComboBox<Integer>>(utAvBrukPicker, SEARCHOPTION.TATTUTAVBRUKAAR));
		drawerContent.getChildren().addAll(utAvBrukPickerLabel, utAvBrukPicker);


		Label uABAArsakLabel = new Label("Tatt ut av bruk, p.g.a.");
		ComboBox<String> uABAArsak = new ComboBox<>();
		uABAArsak.getItems().addAll("Solgt", "Kassert", "Lagt på lager", "Forsvunnet", "Skadet", "Annet");
		uABAArsak.setPromptText("Velg Årsak...");
		advancedFieldsComboBoxString.add(new SearchField<ComboBox<String>>(uABAArsak, SEARCHOPTION.TATTUTAVBRUKAARSAK));
		drawerContent.getChildren().addAll(uABAArsakLabel, uABAArsak);



		return drawerContent;
	}

	private VBox generateTextInputWithLabel(String labelText, String promptText, SEARCHOPTION searchoption) {
		VBox container = new VBox();
		Label label = new Label(labelText);
		label.setFont(new javafx.scene.text.Font(16));
		label.getStyleClass().add("advanced-search-textfield-label");
		TextField field = new TextField();
		field.setPromptText(promptText);
		field.getStyleClass().add("advanced-search-field-textfield");
		advancedFieldsText.add(new SearchField<TextField>(field, searchoption));
		container.getChildren().addAll(label, field);
		return container;
	}

	private VBox generateInputRangeWithLabel(String labelText,  int minRange, int maxRange, int step, SEARCHOPTION searchoption) {
		VBox container = new VBox();
		Label label = new Label(labelText);
		label.setFont(new javafx.scene.text.Font(16));
		label.getStyleClass().add("advanced-search-textfield-label");
		RangeSlider hSlider = new RangeSlider(minRange, maxRange, minRange, maxRange);
		advancedFieldsRange.add(new SearchField<RangeSlider>(hSlider, searchoption));
		container.getChildren().addAll(label, hSlider);
		return container;
	}
	/**
	 * Hjelpefunksjon for å animere åpning eller lukking av et søkeskjema.
	 * TODO burde refaktoreres til en metode med den under.
	 * @param drawer HBox som representerer søkeskjemaet som skal animeres.
	 * @param show Boolean som angir om skjemaet skal vises eller skjules.
	 */
	public void animateDrawerRight(VBox drawer, boolean show){
		TranslateTransition transition = new TranslateTransition(Duration.millis(500), drawer);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		if (show) {
			transition.setFromX(drawer.getWidth() + drawer.getLayoutX());
			transition.setToX(drawer.getLayoutX());
		} else {
			transition.setFromX(drawer.getLayoutX());
			transition.setToX(drawer.getLayoutX() + drawer.getWidth());
			transition.setOnFinished(event -> mc.getClientView().getRoot().setRight(null));
		}
		transition.play();
	}

	/**
	 * Hjelpefunksjon for å animere åpning eller lukking av et søkeskjema.
	 *
	 * @param drawer HBox som representerer søkeskjemaet som skal animeres.
	 * @param show Boolean som angir om skjemaet skal vises eller skjules.
	 */
	public void animateDrawer(HBox drawer, boolean show) {
		TranslateTransition transition = new TranslateTransition(Duration.millis(500), drawer);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		if (show) {
			transition.setFromX(-drawer.getLayoutX());
			transition.setToX(0);
		} else {
			transition.setFromX(0);
			transition.setToX(-drawer.getWidth());
			transition.setOnFinished(event -> mc.getClientView().getRoot().setLeft(null));
		}
		transition.play();
	}




	public void animateSearchForm(HBox searchForm, boolean show) {
		TranslateTransition transition = new TranslateTransition(Duration.millis(300), searchForm);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		if (show) {
			transition.setFromY(50);
			transition.setToY(searchForm.getLayoutY());
		} else {
			transition.setFromY(0);
			transition.setToY(searchForm.getLayoutY());
			transition.setOnFinished(event -> footer.getChildren().remove(searchForm));
		}
		transition.play();
	}
	/**
	 * Henter knappen som aktiverer eller deaktiverer visningen av søkeskjemaet.
	 * Denne knappen brukes til å vise eller skjule det avanserte søkeskjemaet.
	 *
	 * @return En referanse til toggle-knappen for søkeskjemaet.
	 */
	public Button getSearchToggleButton() {
		return searchToggleButton;
	}
	/**
	 * Henter beholderen som inneholder toggle-knappen for søkeskjemaet.
	 * Denne beholderen kan inneholde flere elementer som er relatert til aktivering av søket.
	 *
	 * @return En HBox som inneholder toggle-knappen.
	 */
	public HBox getSearchToggle() {
		return searchToggle;
	}
	/**
	 * Henter hovedsøkeformularet.
	 * Dette formularet inneholder søkefeltet og knapper for å utføre søk og aktivere avanserte søkealternativer.
	 *
	 * @return En HBox som inneholder søkeformularet.
	 */
	public HBox getSearchForm() {
		return searchForm;
	}
	/**
	 * Henter søkeknappen.
	 * Denne knappen brukes for å initiere søket basert på inndataene i søkefeltet.
	 *
	 * @return En referanse til søkeknappen.
	 */
	public Button getSearchButton() {
		return searchButton;
	}
	/**
	 * Henter knappen for å åpne avanserte søkealternativer.
	 * Denne knappen brukes for å utvide søkefeltet til å inkludere flere detaljerte søkekriterier.
	 *
	 * @return En referanse til knappen for avanserte søkealternativer.
	 */
	public Button getSearchOptions() {
		return searchOptions;
	}
	/**
	 * Henter hjelpeknappen for søkefeltet.
	 * Denne knappen kan brukes til å gi brukeren tilleggsinformasjon om hvordan søkefunksjonen skal brukes.
	 *
	 * @return En referanse til hjelpeknappen for søkefeltet.
	 */
	public Button getSearchBarHelpButton(){
		return searchBarHelpButton;
	}
	/**
	 * Henter tekstfeltet for søk.
	 * Brukeren kan skrive inn søkekriterier i dette feltet for å finne relevante inventarobjekter.
	 *
	 * @return Søkefeltets TextField-komponent.
	 */
	public TextField getSearchField() {
		return searchField;
	}

	/**
	 * Henter en liste over de avanserte tekstfeltene for søk.
	 * Disse feltene tillater spesifikasjon av mer spesifikke søkekriterier, som beskrivelse eller plassering.
	 *
	 * @return En liste med avanserte søkefelt for tekst.
	 */
	public ArrayList<SearchField<TextField>> getAdvancedFieldsText() {
		return advancedFieldsText;
	}
	/**
	 * Henter en liste over avanserte RangeSliders for søk.
	 * Disse sliderne lar brukeren spesifisere områder for numeriske verdier som pris eller levetid.
	 *
	 * @return En liste med avanserte søkefelt for RangeSliders.
	 */
	public ArrayList<SearchField<RangeSlider>> getAdvancedFieldsRange() {
		return advancedFieldsRange;
	}
	/**
	 * Henter en liste over ComboBox-felter for heltallsverdier, brukt i det avanserte søkeskjemaet.
	 * Disse feltene kan for eksempel brukes til å velge år for innkjøpsdato eller andre numeriske kriterier.
	 *
	 * @return En liste med ComboBox-felter for heltall.
	 */
	public ArrayList<SearchField<ComboBox<Integer>>> getAdvancedFieldsComboBoxInt() {
		return advancedFieldsComboBoxInt;
	}
	/**
	 * Henter en liste over ComboBox-felter for tekstverdier, brukt i det avanserte søkeskjemaet.
	 * Disse feltene kan for eksempel brukes til å velge kategorier eller typer inventar.
	 *
	 * @return En liste med ComboBox-felter for tekst.
	 */
	public ArrayList<SearchField<ComboBox<String>>> getAdvancedFieldsComboBoxString() {
		return advancedFieldsComboBoxString;
	}
	/**
	 * Henter klientkontrolleren assosiert med denne visningen.
	 * Kontrolleren håndterer all logikk for interaksjon mellom brukergrensesnittet og datamodellene.
	 *
	 * @return Kontrolleren som håndterer søkevisningen.
	 */
	public ClientController getMc() {
		return mc;
	}

	/**
	 * Representerer data for en RangeSlider i det avanserte søkeskjemaet.
	 * Denne klassen lagrer informasjon om etiketten, minimums- og maksimumsverdier, samt stegintervallet for en slider.
	 *
	 * @param labelText Etiketten som vises ved siden av slideren.
	 * @param minValue Minimumsverdien slideren kan ha.
	 * @param maxValue Maksimumsverdien slideren kan ha.
	 * @param step Stegintervallet mellom verdiene.
	 * @param searchoption Den søkeopsjonen som slideren representerer.
	 */
	static class SliderData {
		String labelText;
		int minValue;
		int maxValue;
		int step;
		SEARCHOPTION searchoption;

		SliderData(String labelText, int minValue, int maxValue, int step, SEARCHOPTION searchoption) {
			this.labelText = labelText;
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.step = step;
			this.searchoption = searchoption;
		}
	}


}

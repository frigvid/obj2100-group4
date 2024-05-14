package usn.obj2100.Search;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
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


import usn.obj2100.InventarSearch;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchBarView  {

	private StackPane mainContent;
	private InventarSearch inventarSearch;
	private DropShadow dropShadow;
	private HBox searchForm = buildSearchForm();
	private HBox searchToggle = searchFormToggle();

	private Button searchToggleButton;
	private Button searchOptions;
	private Button searchButton;

	private TextField searchField;

	private ArrayList<SearchField<TextField>> advancedFieldsText;
	private ArrayList<SearchField<RangeSlider>> advancedFieldsRange;
	private ArrayList<SearchField<ComboBox<String>>> advancedFieldsComboBoxString;
	private ArrayList<SearchField<ComboBox<Integer>>> advancedFieldsComboBoxInt;

	private SearchHandlers searchHandlers;

	public SearchBarView(SearchController searchController) {
		this.mainContent = searchController.getClientView().getMainContent();
		this.dropShadow = new DropShadow();
		this.advancedFieldsText = new ArrayList<>();
		this.advancedFieldsRange = new ArrayList<>();
		this.advancedFieldsComboBoxString = new ArrayList<>();
		this.advancedFieldsComboBoxInt = new ArrayList<>();
		init();
	}

	private void init() {
		dropShadow.setColor(Color.GRAY);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setRadius(5.0);
		this.mainContent.getChildren().add(searchToggle);
	}

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


	private HBox buildSearchForm() {
		HBox form = new HBox();
		form.setAlignment(Pos.BOTTOM_CENTER);
		form.getStyleClass().add("search-box");

		searchField = new TextField();
		searchField.getStyleClass().add("text-field");
		searchField.setPrefWidth(400);
		searchField.setPrefHeight(46);
		searchField.setPromptText("Søk etter inventar...");

		searchButton = new Button("Søk");
		searchOptions = new Button("Avansert søk");

		searchOptions.getStyleClass().add("search-options");

		Image image = new Image("search-icon.png");
		ImageView imageView = new ImageView(image);

		imageView.setFitWidth(20);  // bredden på bildet
		imageView.setFitHeight(20); // høyden på bildet

		searchButton.setGraphic(imageView);
		searchButton.getStyleClass().add("search-button");

		HBox buttonGroup = new HBox();
		buttonGroup.getChildren().addAll(searchField, searchOptions, searchButton);
		buttonGroup.setPrefHeight(45);
		buttonGroup.setAlignment(Pos.BOTTOM_CENTER);
		buttonGroup.setEffect(dropShadow); // Legger til skyggeeffekt

		form.getChildren().addAll(buttonGroup);
		form.setSpacing(20);
		form.setPadding(new Insets(20));
		return form;
	}

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

	public void animateDrawer(HBox drawer, boolean show) {
		TranslateTransition transition = new TranslateTransition(Duration.millis(500), drawer);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		if (show) {
			transition.setFromX(-drawer.getLayoutX());
			transition.setToX(0);
		} else {
			transition.setFromX(0);
			transition.setToX(-drawer.getWidth());
			transition.setOnFinished(event -> mainContent.getChildren().remove(drawer));
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
			transition.setOnFinished(event -> mainContent.getChildren().remove(searchForm));
		}
		transition.play();
	}

	public Button getSearchToggleButton() {
		return searchToggleButton;
	}

	public HBox getSearchToggle() {
		return searchToggle;
	}
	public HBox getSearchForm() {
		return searchForm;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public Button getSearchOptions() {
		return searchOptions;
	}

	public TextField getSearchField() {
		return searchField;
	}

	public ArrayList<SearchField<TextField>> getAdvancedFieldsText() {
		return advancedFieldsText;
	}

	public ArrayList<SearchField<RangeSlider>> getAdvancedFieldsRange() {
		return advancedFieldsRange;
	}

	public ArrayList<SearchField<ComboBox<Integer>>> getAdvancedFieldsComboBoxInt() {
		return advancedFieldsComboBoxInt;
	}

	public ArrayList<SearchField<ComboBox<String>>> getAdvancedFieldsComboBoxString() {
		return advancedFieldsComboBoxString;
	}

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

package usn.obj2100.Search;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import usn.obj2100.InventarSearch;

import java.sql.ResultSet;

public class SearchBarView {

	private StackPane mainContent;
	private Button searchButton;
	private Button searchOptions;
	private InventarSearch inventarSearch;
	private DropShadow dropShadow;
	HBox searchForm = buildSearchForm();
	public SearchBarView(StackPane mainContent, InventarSearch inventarSearch) {
		this.mainContent = mainContent;
		this.inventarSearch = inventarSearch;
		this.dropShadow = new DropShadow();
		init();
	}

	private void init() {
		dropShadow.setColor(Color.GRAY);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setRadius(5.0);
		this.mainContent.getChildren().add(searchFormToggle());
	}

	private HBox searchFormToggle(){
		HBox toggleContainer = new HBox();

		Button searchFormToggle = new Button();
		searchFormToggle.setPadding(new Insets(20, 0, 20, 0)); // Setter 20 piksler på toppen og bunnen
		searchFormToggle.getStyleClass().add("search-box-toggle");

		Image image = new Image("search-icon.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(20);  // bredden på bildet
		imageView.setFitHeight(20); // høyden på bildet


		searchFormToggle.setGraphic(imageView);
		searchFormToggle.setEffect(dropShadow);
		searchFormToggle.setAlignment(Pos.CENTER);

		searchFormToggle.setOnAction(event -> {
			if (!mainContent.getChildren().contains(searchForm)) {
				mainContent.getChildren().add(searchForm);
				animateSearchForm(searchForm, true);
			} else {
				animateSearchForm(searchForm, false);
			}
		});



		toggleContainer.getChildren().add(searchFormToggle);
		toggleContainer.setAlignment(Pos.BOTTOM_RIGHT);
		toggleContainer.setPadding(new Insets(0, 20, 20, 0)); // Setter 20 piksler på høyre side og bunnen
		return toggleContainer;
	}


	private HBox buildSearchForm() {
		HBox form = new HBox();
		form.setAlignment(Pos.BOTTOM_CENTER);
		form.getStyleClass().add("search-box");


		TextField searchField = new TextField();
		searchField.getStyleClass().add("text-field");
		searchField.setPrefWidth(400);
		searchField.setPrefHeight(46);
		searchField.setPromptText("Søk etter inventar...");

		searchButton = new Button("Søk");
		searchOptions = new Button("Avansert søk");

		searchOptions.getStyleClass().add("search-options");

		searchOptions.setOnAction(event -> {
			HBox advancedSearchForm = buildAdvancedSearchForm();
			if (!mainContent.getChildren().contains(advancedSearchForm)) {
				mainContent.getChildren().add(advancedSearchForm);
				animateDrawer(advancedSearchForm, true);
			} else {
				animateDrawer(advancedSearchForm, false);
			}
		});

		searchButton.setOnAction(event -> {
			String query = searchField.getText();
			try {
				ResultSet rs = inventarSearch.searchInventar(query);
				while (rs.next()) {
					System.out.println("Beskrivelse: " + rs.getString("beskrivelse"));
				}
			} catch (Exception e) {
				new Alert(Alert.AlertType.ERROR, "Feil under søk: " + e.getMessage()).show();
			}
		});

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

	private HBox buildAdvancedSearchForm() {
		HBox advancedForm = new HBox();
		advancedForm.setPrefWidth(300); // Setter bredde til 35% av hovedinnholdet
		advancedForm.setMaxWidth(300);
		advancedForm.setAlignment(Pos.TOP_LEFT);
		advancedForm.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #dcdcdc; -fx-padding: 20px;");
		// Legg til dine avanserte søkeelementer her

		return advancedForm;
	}


	private void animateDrawer(HBox drawer, boolean show) {
		TranslateTransition transition = new TranslateTransition(Duration.millis(500), drawer);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		if (show) {
			transition.setFromX(-drawer.getWidth());
			transition.setToX(0);
		} else {
			transition.setFromX(0);
			transition.setToX(-drawer.getWidth());
			transition.setOnFinished(event -> mainContent.getChildren().remove(drawer));
		}
		transition.play();
	}




	private void animateSearchForm(HBox searchForm, boolean show) {
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
}

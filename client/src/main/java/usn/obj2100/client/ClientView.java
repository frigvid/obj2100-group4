package usn.obj2100.client;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.LocalDate;

import javafx.scene.paint.Color;
import usn.obj2100.client.GUI.StartView;
import usn.obj2100.client.Inventar.NewInventarView;
import usn.obj2100.client.Search.SearchBarView;
import usn.obj2100.shared.model.Inventar;

public class ClientView {

	private TabPane tabs;
	private StackPane mainContent;
	private SearchBarView searchBarView;
	private ClientController clientController;
	private NewInventarView newInventarView;
	private StartView startView;

	public ClientView(BorderPane root, ClientController clientController) {
		this.tabs = new TabPane();
		this.mainContent = new StackPane();
		this.mainContent.setPickOnBounds(false);
		this.clientController = clientController;
		this.newInventarView = new NewInventarView();
		this.startView = new StartView(clientController);
		mainContent.setAlignment(Pos.TOP_LEFT);
		initializeTabs();

		String css = getClass().getResource("/style.css").toExternalForm();
		mainContent.getStylesheets().addAll("search.css");
		root.setTop(generateMainMenu());
		root.setCenter(tabs);
		root.getStylesheets().add(css);
		root.setBottom(mainContent); //TODO mainContent skal døpes til footer
	}

	private void initializeTabs() {
		HBox startViewContainer = new HBox();
		startViewContainer.getChildren().add(startView);
		startViewContainer.setAlignment(Pos.CENTER);
		HBox.setHgrow(startView, Priority.ALWAYS);
		tabs.getTabs().addAll(
			new Tab("Velkommen", startViewContainer)
		);
		
	}
	
	public MenuBar generateMainMenu(){
		// Lager en meny
		MenuBar menuBar = new MenuBar();
		
		// Lager en filmeny ( fil valg i meny)
		Menu fileMenu = new Menu("Fil");
		menuBar.getMenus().add(fileMenu);
		
		// Lage nytt inventar menyvalg
		MenuItem newItem = new MenuItem("Nytt inventar");
		newItem.setOnAction(event -> System.out.println("New File Created!"));
		
		// Generer raport
		MenuItem rapport = new MenuItem("Inventar-rapport");
		rapport.setOnAction(event -> System.out.println("Rapport!"));
		
		// Menyvalg for å avslutte
		MenuItem exitItem = new MenuItem("Avslutt");
		exitItem.setOnAction(event -> System.exit(0));
		
		// Legge til menyer på filmeny.
		fileMenu.getItems().addAll(newItem, rapport,  new SeparatorMenuItem(), exitItem);
		
		// Legge til flere menyer på toppnivå i meny.
		
		Menu helpMenu = new Menu("Help");
		MenuItem help = new MenuItem("Hjelp til å bruke appen");
		MenuItem omOss = new MenuItem("Om oss");
		helpMenu.getItems().addAll(help, omOss);
		
		menuBar.getMenus().add(helpMenu);
		return menuBar;
	}
	

	public TabPane getTabs() {
		return tabs;
	}

	public void setTab (int index) {
		tabs.getSelectionModel().select(index);
	}
	
	public void setNewTabContentNewInventar ( HBox content) {
		content.setAlignment(Pos.CENTER);
		Tab tempTab = new Tab("Nytt Søk", content);
		tabs.getTabs().add(tempTab);
		setTab(tabs.getTabs().size()-1);
	}
	
	public void setNewTabContent ( Node content) {

		VBox searchResults = clientController.getSearchController().getSearchResultsView();
		Tab tempTab = new Tab("Nytt Søk", searchResults);
		tabs.getTabs().add(tempTab);
		setTab(tabs.getTabs().size()-1);
	}

	public void updateTabContent ( Node content) {
		//tabs.getSelectionModel().getSelectedItem().setContent(content);
	}


	public StackPane getMainContent() {
		return mainContent;
	}

	public SearchBarView getSearchBarView() {
		return searchBarView;
	}
	
	public NewInventarView getNewInventarView() { return newInventarView; }

	public void setSearchBarView(SearchBarView searchBarView) {
		this.searchBarView = searchBarView;
	}
	
	
}

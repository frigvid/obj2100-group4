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
import usn.obj2100.client.Inventar.Views.DeleteInventarView;
import usn.obj2100.client.Inventar.Views.EditInventarView;
import usn.obj2100.client.Inventar.Views.SelectedInventarView;
import usn.obj2100.client.Search.SearchBarView;
import usn.obj2100.client.Search.SearchHandlers;
import usn.obj2100.shared.model.Inventar;

public class ClientView {

	private BorderPane root; //Bunnnivå root
	private TabPane tabs;
	private StackPane footer;
	private ClientController clientController;
	private ScreenController screen;
	private StartView startScreen;
	private SearchBarView searchBar;
	private int searchTabIndex = 0;

	public ClientView( ClientController clientController) {
		this.root = clientController.getRoot();
		this.tabs = new TabPane();
		this.footer = new StackPane();
		this.footer.setPickOnBounds(false);
		this.clientController = clientController;
		this.screen = clientController.getScreen();
		this.startScreen = new StartView(clientController);
		this.searchBar = new SearchBarView(clientController, this);
		screen.setSearchBar(searchBar);
		screen.setStartScreen(startScreen);

		footer.setAlignment(Pos.TOP_LEFT);
		initializeTabs();


		footer.getStylesheets().addAll("search.css");
		root.setTop(generateMainMenu());
		root.setCenter(tabs);
		root.getStylesheets().add("style.css");
		root.setBottom(footer); //TODO mainContent skal døpes til footer

	}

	private void initializeTabs() {
		HBox startViewContainer = new HBox();
		startViewContainer.getChildren().add(screen.getStartScreen());
		startViewContainer.setAlignment(Pos.CENTER);

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
	
	public void setEditInventarTabContent(Inventar inventar){
		HBox content = new HBox();
		content.setAlignment(Pos.CENTER);
		clientController.setSelectedInvetar(inventar);
		content.getChildren().add(new EditInventarView(clientController));
		String tittel = inventar.getBeskrivelse().length() > 15 ? inventar.getBeskrivelse().substring(0, 15) : inventar.getBeskrivelse();
		Tab tempTab = new Tab("Rediger " + tittel, content);
		tabs.getTabs().add(tempTab);
		setTab(tabs.getTabs().size()-1);
	}
	
	public void setViewInventarTabContent(Inventar inventar){
		HBox content = new HBox();
		content.setAlignment(Pos.CENTER);
		clientController.setSelectedInvetar(inventar);
		content.getChildren().add(new SelectedInventarView(clientController));
		String tittel = inventar.getBeskrivelse().length() > 15 ? inventar.getBeskrivelse().substring(0, 15) : inventar.getBeskrivelse();
		Tab tempTab = new Tab("Se på " + tittel, content);
		tabs.getTabs().add(tempTab);
		setTab(tabs.getTabs().size()-1);
	}
	
	public void setDeleteInventarTabContent(Inventar inventar){
		
		HBox content = new HBox();
		content.setAlignment(Pos.CENTER);
		clientController.setSelectedInvetar(inventar);
		content.getChildren().add(new DeleteInventarView(clientController));
		String tittel = inventar.getBeskrivelse().length() > 15 ? inventar.getBeskrivelse().substring(0, 15) : inventar.getBeskrivelse();
		Tab tempTab = new Tab("Slett " + tittel, content);
		tabs.getTabs().add(tempTab);
		setTab(tabs.getTabs().size()-1);
	}
	
	public void setNewTabContentNewInventar ( HBox content) {
		content.setAlignment(Pos.CENTER);
		Tab tempTab = new Tab("Nytt inventar", content);
		tabs.getTabs().add(tempTab);
		setTab(tabs.getTabs().size()-1);
	}

	public void setNewTabContentAllInventar(Node content){
		Tab tempTab = new Tab("Alle inventar", content);
		tabs.getTabs().add(1, tempTab);
		setTab(tabs.getTabs().size()-1);
	}

	public void setNewTabContent ( String content) {
		VBox searchResults = clientController.getScreen().getSearchResultScreen(content);
		Tab tempTab = new Tab(content, searchResults);
		if (searchTabIndex > 0){
			updateTabContent(tempTab, searchTabIndex);
			return;
		}

		tabs.getTabs().add(tempTab);
		searchTabIndex = tabs.getTabs().size()-1;
		setTab(searchTabIndex);
	}

	public void updateTabContent ( Tab content, int index) {
		tabs.getTabs().remove(index);
		tabs.getTabs().add(index,content);
		tabs.getSelectionModel().select(index);
	}


	public StackPane getFooter() {
		return footer;
	}

	public SearchBarView getSearchBar(){
		return searchBar;
	}





	public BorderPane getRoot(){
		return root;
	}
	
}

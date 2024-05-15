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

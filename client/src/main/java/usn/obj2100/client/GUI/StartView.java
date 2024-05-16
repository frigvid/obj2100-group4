package usn.obj2100.client.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import usn.obj2100.client.ClientController;


public class StartView extends HBox
{
	
	private ClientController mc;
	/**
	 * Startskjermen i klientprogram. Er mulig å søke her, lage ny Inventar, se alle inventar.
	 *
	 * Eksempel bruk:
	 * {@snippet id = "startView" lang = "java" group = "GUI":
	 * 	import usn.obj2100.client.GUI.StartView;
	 *
	 * 	StartView startView = new StartView(mc);
	 *}
	 * @extends HBox - legges til i root BorderPane for fremvisning!
	 * @param ClientController mc: Main controller.
	 */
	public StartView(ClientController mc){
		this.mc = mc;
		getChildren().add(renderStartScreen());
		}

	/**
	 * Bygger startskjermen.
	 * @return VBox
	 */
	private VBox renderStartScreen() {
		VBox form = new VBox(10);
		form.setPadding(new Insets(20));
		form.setAlignment(Pos.CENTER);
		form.setFillWidth(true);
		int antInv = 30;
	
		Label antInventar = new Label("Antall inventar: " + antInv);
		antInventar.setFont(Font.font(20));
			antInventar.setAlignment(Pos.CENTER);
			
		int aktInv = 25;
		Label antAktiveInventar = new Label("Antall Aktive Inventar: " + aktInv);
		antAktiveInventar.setFont(Font.font(20));
			antAktiveInventar.setAlignment(Pos.CENTER);
			
		HBox inventarStats = new HBox();
		inventarStats.getChildren().addAll(antInventar, antAktiveInventar);
		inventarStats.setSpacing(20);
		inventarStats.setAlignment(Pos.CENTER);
		
		
		Button addNewInv = new Button("Legg til nytt inventar");
		addNewInv.getStyleClass().add("blue-button");
		addNewInv.setTooltip(new Tooltip("Trykk for å legge til nytt inventar"));
		addNewInv.setAlignment(Pos.CENTER);
		
		addNewInv.setOnMouseReleased(event -> {
			mc.getClientView().setNewTabContentNewInventar(mc.getScreen().getNewInventarScreen());
		});

		Button seeAllInv = new Button("Se alle inventar");
		seeAllInv.getStyleClass().add("blue-button");
		seeAllInv.setTooltip(new Tooltip("Trykk for å se alle inventar"));
		seeAllInv.setAlignment(Pos.CENTER);

			seeAllInv.setOnMouseReleased(event -> {
				mc.getClientView().setNewTabContentAllInventar(mc.getScreen().getViewAllScreen());
			});

		form.getChildren().addAll(inventarStats, addNewInv, seeAllInv);
		return form;
		}
}

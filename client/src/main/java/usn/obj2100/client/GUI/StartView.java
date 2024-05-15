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
	
	public StartView(ClientController mc){
		this.mc = mc;
		getChildren().add(renderStartScreen());
		}
	
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
		addNewInv.getStyleClass().add("nyttInventarBtn");
		addNewInv.setTooltip(new Tooltip("Trykk for å legge til nytt inventar"));
		addNewInv.setAlignment(Pos.CENTER);
		
		addNewInv.setOnMouseReleased(event -> {
			mc.getClientView().setNewTabContentNewInventar(mc.getClientView().getNewInventarView());
		});
		
		Button seeAllInv = new Button("Se alle inventar");
		seeAllInv.getStyleClass().add("seeAllInventarBtn");
		seeAllInv.setTooltip(new Tooltip("Trykk for å se alle inventar"));
		seeAllInv.setAlignment(Pos.CENTER);
		
		form.getChildren().addAll(inventarStats, addNewInv, seeAllInv);
		return form;
		}
}

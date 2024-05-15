package usn.obj2100.client.Inventar.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import usn.obj2100.client.ClientController;
import usn.obj2100.client.GUI.KasseringCombo;
import usn.obj2100.shared.model.Inventar;

public class DeleteInventarView extends HBox
{
	private ClientController mc;
	private Inventar selectedInventar;
	
	public DeleteInventarView(ClientController mc){
		this.mc = mc;
		this.selectedInventar = mc.getSelectedInvetar();
		this.getChildren().add(generateDeleteView());

	}
	
	
	private VBox generateDeleteView(){
			VBox form = new VBox(10);
			form.setPadding(new Insets(20));
			form.setAlignment(Pos.CENTER);
			form.setSpacing(20);
			
			Label title = new Label("Inventar-beskrivelse: " + selectedInventar.getBeskrivelse());
			title.setFont(Font.font("times new roman", FontWeight.BOLD, 20));
			
			KasseringCombo kassering = new KasseringCombo();
			
			Button deleteButton = new Button("Kasser inventar");
			deleteButton.getStyleClass().add("delete-button");


			deleteButton.setOnMouseClicked(e
				->{System.out.println("delete");});
				
			form.getChildren().addAll(title, kassering, deleteButton);
			return form;
	}
	
}

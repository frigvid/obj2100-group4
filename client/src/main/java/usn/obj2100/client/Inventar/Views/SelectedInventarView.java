package usn.obj2100.client.Inventar.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import usn.obj2100.client.ClientController;
import usn.obj2100.shared.model.Inventar;

public class SelectedInventarView extends HBox
{
	private Inventar selectedInventar;
	final private ClientController mc;
	
	public SelectedInventarView(ClientController clientController){
		this.mc = clientController;
		this.selectedInventar = mc.getSelectedInvetar();
		getChildren().add(renderEditScreen());
		
		if(selectedInventar != null){
			renderEditScreen();
		}else{
			renderNoSelection();
		}
		
	}
	
	private VBox renderNoSelection(){
		VBox form = new VBox(10);
		form.setPadding(new Insets(20));
		form.setAlignment(Pos.CENTER);
		form.setSpacing(20);
		
		Label noSelectionError = new Label("Du har ikke valg inventar!");
		noSelectionError.setFont(Font.font(25));
		noSelectionError.setAlignment(Pos.CENTER);
		
		Button returnerTilStart = new Button("Returner");
		returnerTilStart.setTooltip(new Tooltip("Returner til startskjerm"));
		returnerTilStart.setAlignment(Pos.CENTER);
		returnerTilStart.getStyleClass().add("backToStartButton");
		
		form.getChildren().addAll(noSelectionError, returnerTilStart);
		
		return form;
	}
	
	
	private VBox renderEditScreen() {
		VBox form = new VBox(10);
		form.setPadding(new Insets(20));
		form.setAlignment(Pos.CENTER);
		
		Label selectedType = bigLabel("Møbler"); // TODO gjøre dynamisk, skal lastes fra db..
		
		
		
		Label kategori = bigLabel("testkategori"); //TODO gjøre dynamisk!!!
		
		
		Label innkjøpsDato = bigLabel(selectedInventar.getInnkjopsdato() + "");
		Label priceField = bigLabel("" + selectedInventar.getInnkjopspris());
		Label locationField = bigLabel("" +selectedInventar.getPlassering());
		Label quantityField = bigLabel("" + selectedInventar.getAntall());
		Label lifespanField = bigLabel("" + selectedInventar.getForventetLevetid());
		
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		Label descriptionField = new Label(selectedInventar.getBeskrivelse());
		
		grid.addRow(0, bigLabel("Beskrivelse: "), descriptionField);
		grid.addRow(1, bigLabel("Type:"), selectedType);
		grid.addRow(2, bigLabel("Kategori:"), kategori);
		grid.addRow(3, bigLabel("Innkjøpsdato:"), innkjøpsDato);
		grid.addRow(4, bigLabel("Innkjøpspris:"), priceField);
		grid.addRow(5, bigLabel("Plassering:"), locationField);
		grid.addRow(6, bigLabel("Antall:"), quantityField);
		grid.addRow(7, bigLabel("Forventet levetid:"), lifespanField);
		
		
		form.getChildren().addAll(grid);
		return form;
	}
	
	
	private Label bigLabel (String labelText){
		Label tempLabel = new Label(labelText);
		tempLabel.setFont(Font.font("times new roman", FontWeight.BOLD, 22));
		return tempLabel;
	}
}

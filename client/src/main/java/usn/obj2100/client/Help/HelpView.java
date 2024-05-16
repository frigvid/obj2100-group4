package usn.obj2100.client.Help;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelpView extends  VBox{
	/**
	 * Lager en bruksanvisning for søkefeltet.
	 *
	 * Eksempel bruk:
	 * {@snippet id = "helpView" lang = "java" group = "GUI":
	 * 	import usn.obj2100.client.Help.HelpView;
	 *
	 * 	HelpView typeCombo = new HelpView();
	 *}
	 * @extends VBox
	 */

	public HelpView(){
		this.getChildren().addAll(getSearchHelp());
		this.setMaxWidth(250);
	}


	//Generer hjelpe-tekst, vises frem i en drawer på forespørsel når man klikker på spm.tegn i søkefelt.
	private VBox getSearchHelp(){
		VBox helpBox = new VBox();
		helpBox.setAlignment(Pos.TOP_LEFT);
		helpBox.setPadding(new Insets(10));
		helpBox.setSpacing(40);

		Label helpTitle = new Label("Hjelp til søk:");
		helpTitle.setFont(Font.font("Times new roman", FontWeight.BOLD, 30));

		Label searchByBeskrivelse = new Label("Søk på beskrivelse:");
		searchByBeskrivelse.setFont(Font.font("Times new roman", FontWeight.BOLD, 24));
		searchByBeskrivelse.setWrapText(true);

		Label searchByTyping = new Label("Ved å skrive i søkefeltet, søker du på beskrivelse!");
		searchByTyping.setFont(Font.font(16));
		searchByTyping.setWrapText(true);

		VBox mainSearchHelp = new VBox(searchByBeskrivelse, searchByTyping);
		mainSearchHelp.setSpacing(10);

		Label searchByAdvancedTitle = new Label("Avansert søk:");
		searchByBeskrivelse.setFont(Font.font("Times new roman", FontWeight.BOLD, 24));
		searchByAdvancedTitle.setWrapText(true);

		Label searchByAdvanced = new Label("Ved å trykke på grønn knapp i søkefeldtet, kan du gi flere instillinger til ditt søk.");
		searchByTyping.setFont(Font.font(16));
		searchByAdvanced.setWrapText(true);

		VBox searchAdvancedHelp = new VBox(searchByAdvancedTitle, searchByAdvanced);
		searchAdvancedHelp.setSpacing(10);

		helpBox.getChildren().addAll(helpTitle, mainSearchHelp, searchAdvancedHelp);

		return helpBox;
	}
}

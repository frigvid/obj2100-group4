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
import usn.obj2100.shared.model.InventarExtended;

/**
 * Denne klassen representerer en visning for sletting av inventarobjekter.
 * Den tilbyr et brukergrensesnitt der brukere kan slette et spesifikt inventar fra systemet.
 * <p>
 * Visningen inneholder en beskrivelse av inventaret, en kombinasjonsboks for å velge kasseringstype,
 * og en knapp for å bekrefte slettingen.
 * <p>
 * Eksempel på bruk:
 * <pre>
 * {@code
 * ClientController clientController = new ClientController();
 * DeleteInventarView deleteInventarView = new DeleteInventarView(clientController);
 * }
 * </pre>
 *
 * @param mc Klientkontrolleren som håndterer interaksjoner og gir tilgang til det valgte inventaret.
 */
public class DeleteInventarView extends HBox
{
	private ClientController mc;
	private InventarExtended selectedInventar;
	/**
	 * Konstruerer visningen for sletting av inventar.
	 * Initialiserer visningen med det valgte inventaret hentet fra klientkontrolleren.
	 *
	 * @param mc Klientkontrolleren som brukes til å hente og håndtere det valgte inventaret.
	 */
	public DeleteInventarView(ClientController mc){
		this.mc = mc;
		this.selectedInventar = mc.getSelectedInvetar();
		this.getChildren().add(generateDeleteView());
	}


	/**
	 * Oppretter og konfigurerer GUI-elementene som brukes for sletting av inventar.
	 * Dette inkluderer en tittel med inventarets beskrivelse, en kombinasjonsboks for kassering,
	 * og en sletteknapp.
	 * <p>
	 * Tittelen reflekterer den nåværende beskrivelsen av det valgte inventaret.
	 * Kombinasjonsboksen lar brukeren velge hvordan inventaret skal registreres kassert.
	 * Sletteknappen aktiverer slettingen av inventaret.
	 *
	 * @return En VBox som inneholder alle nødvendige GUI-komponenter for sletteprosessen.
	 */
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
				->{
				System.out.println(selectedInventar);

				//mc.getInventarController().deleteInventar(selectedInventar);

				System.out.println("delete");

			});
				
			form.getChildren().addAll(title, kassering, deleteButton);
			return form;
	}
	
}

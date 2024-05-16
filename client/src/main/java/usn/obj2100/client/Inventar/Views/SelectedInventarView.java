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

/**
 * Denne klassen representerer visningen for å vise detaljert informasjon om et valgt inventarobjekt.
 * Visningen inneholder alle relevante detaljer om inventaret, inkludert type, kategori, innkjøpsdato, pris og mer.
 * <p>
 * Hvis det ikke er valgt noe inventar, vises en feilmelding med en knapp for å returnere til startskjermen.
 * <p>
 * Eksempel på bruk:
 * <pre>
 * {@code
 * ClientController clientController = new ClientController();
 * SelectedInventarView selectedInventarView = new SelectedInventarView(clientController);
 * }
 * </pre>
 *
 * @param clientController Kontrolleren som håndterer alle interaksjoner mellom brukergrensesnittet og applikasjonens data.
 */
public class SelectedInventarView extends HBox
{
	private Inventar selectedInventar;
	final private ClientController mc;
	/**
	 * Konstruerer en ny visning basert på det valgte inventaret fra klientkontrolleren.
	 * Denne metoden laster inn og viser det valgte inventaret eller en feilmelding hvis ingen inventar er valgt.
	 *
	 * @param clientController Kontrolleren som håndterer logikken for valg og håndtering av inventar.
	 */
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
	/**
	 * Oppretter en visning som informerer brukeren om at ingen inventar er valgt.
	 * Denne visningen inneholder en feilmelding og en knapp som lar brukeren returnere til startskjermen.
	 *
	 * @return En VBox inneholdende en feilmelding og en returknapp.
	 */
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

	/**
	 * Oppretter og returnerer hovedgrensesnittet for å vise detaljer om det valgte inventaret.
	 * Denne metoden setter opp en visning som inneholder alle viktige detaljer om inventaret, organisert i et GridPane.
	 *
	 * @return En VBox som inneholder et GridPane med detaljer om det valgte inventaret.
	 */
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

	/**
	 * Hjelpemetode for å opprette en stor label med spesifisert tekst.
	 * Setter skrifttype og størrelse for label.
	 *
	 * @param labelText Teksten som skal vises i labelen.
	 * @return En label med forhåndsdefinert stil.
	 */
	private Label bigLabel (String labelText){
		Label tempLabel = new Label(labelText);
		tempLabel.setFont(Font.font("times new roman", FontWeight.BOLD, 22));
		return tempLabel;
	}
}

package usn.obj2100.client.Inventar;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import usn.obj2100.client.ClientController;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.model.Inventar;
import usn.obj2100.shared.model.InventarExtended;

public class InventarController {
	
	private ClientController mc;
	private InventarExtended inventar;


	/**
	 * Controller til Inventar på klient.
	 *
	 * Eksempel bruk:
	 * {@snippet id = "inventarController" lang = "java" group = "Inventar":
	 * 	import usn.obj2100.client.Inventar.InventarController;
	 *
	 * 	InventarController inventarController = new InventarController();
	 *}
	 * @param ClientController mc
	 * @note kunne brukt extends fremfor dependency injection her...
	 */

	public InventarController( ClientController mc ) {
		this.mc = mc;
	}


	//kall til server for å legge til nytt inventar
	public void addInventar(Inventar newInventar){
		//setInventar(inventar);
		try {
			Object result = mc.getServerConnection().request(Command.CREATE, newInventar);
			if(result == null) {
				System.out.println("ERROR");
			}
			System.out.println(result);
		} catch (Exception e){
			System.out.println("Error from InventarController:client ");
			e.printStackTrace();
		}
	}
	//kall til server for å slette inventar
	public void deleteInventar(int inventar) {
		//setInventar(inventar);
		Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
		confirmDialog.setTitle("Bekreft sletting");
		confirmDialog.setHeaderText("Slette inventar - ikke ferdig modul");
		confirmDialog.setContentText("Vær klar over at denne slettingen vill fryse programmet, og at du må starte " +
			"programmet på nytt om du forstetter, og at inventaren blir slettet, vill du forstette?");

		// Adding buttons to the confirmation dialog
		confirmDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		// Displaying the dialog and capturing the user's response
		confirmDialog.showAndWait().ifPresent(response -> {
			if (response == ButtonType.YES) {
				// User chose YES
				try {
					// Requesting the server to delete the inventar
					mc.getServerConnection().request(Command.DELETE, inventar);
					System.out.println("Inventar slettet: " + inventar);
				} catch (Exception e) {
					System.err.println("Feil under sletting av inventar: " + e.getMessage());
				}
			} else {
				// User chose NO or closed the dialog
				System.out.println("Sletting avbrutt");
			}
		});

	}
	//kall til server for å redigere inventar
	public void editInventar(Inventar inventar) {
		//setInventar(inventar);
		mc.getServerConnection().request(Command.UPDATE, inventar);
	}
	//kall til MainController for å velge en spesifikk inventar fra søkeresultat.
	public void selectInventar(InventarExtended inventar) {
		setInventar(inventar);
		mc.setSelectedInvetar(inventar);
	}
	// setter en inventar til nåværende inventar i InventarController.
	public void setInventar(InventarExtended newInventar){
		inventar = newInventar;
	}
	
}

package usn.obj2100.client.Inventar;

import usn.obj2100.client.ClientController;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.model.Inventar;

public class InventarController {
	
	private ClientController mc;
	private Inventar inventar;


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
	public void addInventar(Inventar inventar) {
		setInventar(inventar);
		mc.getServerConnection().request(Command.CREATE, inventar);
	}
	//kall til server for å slette inventar
	public void deleteInventar(Inventar inventar) {
		setInventar(inventar);
		mc.getServerConnection().request(Command.DELETE, inventar);
	}
	//kall til server for å redigere inventar
	public void editInventar(Inventar inventar) {
		setInventar(inventar);
		mc.getServerConnection().request(Command.UPDATE, inventar);
	}
	//kall til MainController for å velge en spesifikk inventar fra søkeresultat.
	public void selectInventar(Inventar inventar) {
		setInventar(inventar);
		mc.setSelectedInvetar(inventar);
	}
	// setter en inventar til nåværende inventar i InventarController.
	public void setInventar(Inventar newInventar){
		inventar = newInventar;
	}
	
}

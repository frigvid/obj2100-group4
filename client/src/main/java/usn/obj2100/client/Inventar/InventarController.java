package usn.obj2100.client.Inventar;

import usn.obj2100.client.ClientController;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.model.Inventar;

public class InventarController {
	
	private ClientController mc;
	private Inventar inventar;
	
	public InventarController( ClientController mc ) {
		this.mc = mc;
	}
	
	public void addInventar(Inventar inventar) {
		setInventar(inventar);
		mc.getServerConnection().request(Command.CREATE, inventar);
	}
	
	public void deleteInventar(Inventar inventar) {
		setInventar(inventar);
		mc.getServerConnection().request(Command.DELETE, inventar);
	}
	
	public void editInventar(Inventar inventar) {
		setInventar(inventar);
		mc.getServerConnection().request(Command.UPDATE, inventar);
	}
	
	public void selectInventar(Inventar inventar) {
		setInventar(inventar);
		mc.setSelectedInvetar(inventar);
	}
	
	public void setInventar(Inventar newInventar){
		inventar = newInventar;
	}
	
}

package usn.obj2100.client.GUI;

import javafx.scene.control.TextField;

public class EditTextField extends TextField
{
	private String value;
	private String newValue;
	/**
	 * Et oppdaterbardt tekstfeldt ment for redigerings feldt.
	 *
	 * Eksempel bruk:
	 * {@snippet id = "editBeskrivelse" lang = "java" group = "GUI":
	 * 	import usn.obj2100.client.GUI.EditTextField;
	 *
	 * 	EditTextField editBeskrivelse = new EditTextField("Lagret db verdi ment for redigering");
	 *
	 * 	editBeskrivelse.getValue()
	 *  	editBeskrivelse.setNewValue("Ny beskrivelse")
	 *}
	 *
	 * @param value teksten som er lagret for feldtet
	 * @param newValue Den nye verdien for feldt.
	 */
	public EditTextField(String value){
		this.value = value;
		this.setText(value);
		this.newValue = "";
	}

	/**
	 * henter lagret db-verdi
	 * @return String
	 */
	public String getValue(){
		return value;
	}

	/**
	 * setter ny verdi, som skal brukes til oppdatering av feltet i db.
	 * @param newValue
	 */
	
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}
}

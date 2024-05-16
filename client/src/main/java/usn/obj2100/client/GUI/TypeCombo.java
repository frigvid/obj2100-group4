package usn.obj2100.client.GUI;

import javafx.scene.control.ComboBox;
import usn.obj2100.shared.Type;

public class TypeCombo<String> extends ComboBox<String>
{
	private String[] typer;
	private String type;
	/**
	 * Combobox for redigering av inventartyper.
	 *
	 * Eksempel bruk:
	 * {@snippet id = "typeCombo" lang = "java" group = "GUI":
	 * 	import usn.obj2100.client.GUI.TypeCombo;
	 *
	 * 	TypeCombo typeCombo = new TypeCombo(typer, selectedType);
	 *}
	 * @extends ComboBox
	 * @param typer - typene combobox skal laste
	 * @param selectedType - typen som er valgt fra før. (den som er i db)
	 */
	public TypeCombo(String[] typer, String selectedType){
		this.typer = typer;
		this.type = selectedType;
		initCombo();
	}
	//initierer combo!
	private void initCombo(){
		for(String type: typer){
			getItems().add(type);
		}
		setValue(type);
	}
}

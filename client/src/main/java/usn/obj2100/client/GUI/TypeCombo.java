package usn.obj2100.client.GUI;

import javafx.scene.control.ComboBox;
import usn.obj2100.shared.Type;

public class TypeCombo<String> extends ComboBox<String>
{
	private String[] typer;
	private String type;
	public TypeCombo(String[] typer, String selectedType){
		this.typer = typer;
		this.type = selectedType;
		initCombo();
	}
	
	private void initCombo(){
		for(String type: typer){
			getItems().add(type);
		}
		setValue(type);
	}
}

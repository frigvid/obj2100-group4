package usn.obj2100.client.GUI;

import javafx.scene.control.ComboBox;
import usn.obj2100.shared.Type;

public class TypeCombo<String> extends ComboBox<String>
{
	private String[] typer;
	public TypeCombo(String[] typer){
		this.typer = typer;
		initCombo();
	}
	
	private void initCombo(){
		for(String type: typer){
			getItems().add(type);
		}
	}
}

package usn.obj2100.client.GUI;

import javafx.scene.control.TextField;

public class EditTextField extends TextField
{
	private String value;
	private String newValue;
	public EditTextField(String value){
		this.value = value;
		this.newValue = "";
	}
	
	public String getValue(){
		return value;
	}
	
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}
}

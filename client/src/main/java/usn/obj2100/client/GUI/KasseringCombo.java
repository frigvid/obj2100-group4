package usn.obj2100.client.GUI;

import javafx.scene.control.ComboBox;

public class KasseringCombo extends ComboBox
{
		private String[] kasseringsAarsaker = {"Solgt", "Kassert" , "Lagt på lager", "Ødelagt"};
		
		public KasseringCombo ()
		{
			initCombo();
		}
		
		private void initCombo(){
			for(String aarsak: this.kasseringsAarsaker){
				getItems().add(aarsak);
			}
		}
		
		
}

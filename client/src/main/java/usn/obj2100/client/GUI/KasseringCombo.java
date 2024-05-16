package usn.obj2100.client.GUI;

import javafx.scene.control.ComboBox;

public class KasseringCombo extends ComboBox
{
		private String[] kasseringsAarsaker = {"Solgt", "Kassert" , "Lagt på lager", "Ødelagt"};
	   /**
		* En oppdaterbardt ComboBox ment for redigerings feldt.
		*
		* Eksempel bruk:
		* {@snippet id = "kasseringCombo" lang = "java" group = "GUI":
		* 	import usn.obj2100.client.GUI.KasseringCombo;
		*
		* 	KasseringCombo editKasering = new KasseringCombo();
		*
		*}
		*
		* er hardkodet med faste verdier, skalerer ikke bra !
		*/
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

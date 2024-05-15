package usn.obj2100.client.GUI;

import javafx.scene.control.ComboBox;

public class KategoriCombo<String> extends ComboBox<String>
{
	private String[] kategorier;
	
	public KategoriCombo(String[] kategorier){
		this.kategorier = kategorier;
		initCombo();
	}
	public KategoriCombo(){
	
	}
	private void initCombo(){
		for(String kategori: this.kategorier){
			getItems().add(kategori);
		}
	}
	
	public void setKategorier(String[] kategorier){
		this.kategorier = kategorier;
		for(String kategori: this.kategorier){
			getItems().add(kategori);
		}
	}
}

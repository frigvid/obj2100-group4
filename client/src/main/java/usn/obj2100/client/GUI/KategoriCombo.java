package usn.obj2100.client.GUI;

import javafx.scene.control.ComboBox;

public class KategoriCombo<String> extends ComboBox<String>
{
	private String[] kategorier;

	/**
	 * En oppdaterbardt combobox ment for redigerings feldt.
	 *
	 * Eksempel bruk:
	 * {@snippet id = "editKategori" lang = "java" group = "GUI":
	 * 	import usn.obj2100.client.GUI.KategoriCombo;
	 *
	 * 	KategoriCombo editKategori = new KategoriCombo(kategorier);
	 *
	 * 	editKategori.setKategorier(kategorier);
	 *
	 *}
	 *
	 * @param kategorier array av string: array av kategorier...
	 */
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

	//for å sette kategorier
	public void setKategorier(String[] kategorier){
		this.kategorier = kategorier;
		for(String kategori: this.kategorier){
			getItems().add(kategori);
		}
	}
}

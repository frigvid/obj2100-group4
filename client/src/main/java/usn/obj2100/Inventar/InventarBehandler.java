package usn.obj2100.Inventar;

import java.util.Date;

public class InventarBehandler {

	public boolean opprettInventar() { //returnerer true hvis inventar er opprettet
		// skap inventar new Inventar();
		//

		return false;
	}

	public Inventar søkInventar(){
		// søk etter inventar
		// returner inventar //returnerer null hvis ikke funnet
		return null;
	}

	public boolean endreInventar( String beskrivelse, double innkjøpsPris, Date innkjøpsDato, String plassering, Object kategori, Integer forventetLevetid) { //returnerer true hvis inventar er endret
		// endre inventar
		// Inndata - validering
		// forespørr endring fra server
		// returner true //returnerer false hvis ikke endret
		return false;
	}

	public boolean slettInventar() { //returnerer true hvis inventar er slettet
		// slett inventar
		// Inndata - validering
		// forespørr sletting fra server
		// returner true //returnerer false hvis ikke slettet
		return false;
	}
}

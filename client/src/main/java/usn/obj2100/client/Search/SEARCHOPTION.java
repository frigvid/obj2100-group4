package usn.obj2100.client.Search;
/**
 * Enum som definerer ulike søkealternativer brukt i søkefunksjonaliteten.
 * Hver konstant i denne enumen representerer et bestemt felt eller kriterium som kan brukes
 * for å filtrere eller spesifisere søk i inventaroversikten.
 */
public enum SEARCHOPTION {
	/** Søk basert på beskrivelsen av et inventarobjekt. */
	BESKRIVELSE,

	/** Søk basert på typen av inventar, som f.eks. møbler eller teknisk utstyr. */
	TYPE,

	/** Søk basert på kategorien av inventar innenfor en gitt type. */
	KATEGORI,

	/** Søk basert på årstallet da inventaret ble innkjøpt. */
	INNKJOPSDATO,

	/** Søk basert på prisklassen til inventaret. */
	PRIS,

	/** Søk basert på plasseringen av inventaret, som kan inkludere bygning, etasje osv. */
	PLASSERING,

	/** Søk basert på antallet av en spesifikk inventartype i beholdning. */
	ANTALL,

	/** Søk basert på forventet levetid av inventaret, typisk brukt for varer som har utløpsdato eller nedbrytingstid. */
	LEVETID,

	/** Søk basert på forventet tidspunkt for kassering av inventaret. */
	FORVENTETKASSERING,

	/** Søk for å finne inventar som er i bruk. */
	IBRUK,

	/** Søk for å finne inventar som ikke er i bruk. */
	IKKEIBRUK,

	/** Søk basert på årsaken til at et inventarobjekt ble tatt ut av bruk. */
	TATTUTAVBRUKAARSAK,

	/** Søk basert på året et inventarobjekt ble tatt ut av bruk. */
	TATTUTAVBRUKAAR,

	/** Generelt søk som kan brukes for å initiere en omfattende søkeoperasjon basert på flere felt samtidig. */
	SEARCH
}

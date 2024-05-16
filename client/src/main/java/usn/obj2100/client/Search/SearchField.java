package usn.obj2100.client.Search;
/**
 * Generisk klasse som representerer et felt og tilhørende søkeopsjon.
 * Denne klassen brukes for å lagre informasjon om spesifikke felt som er involvert i søkeprosesser,
 * hvor hvert felt er assosiert med en bestemt søkeopsjon som definerer hvordan søket skal håndteres.
 *
 * @param <T> Typen til feltet som er involvert i søket.
 */
public class SearchField<T> {
		private T field;
		private SEARCHOPTION option;
	/**
	 * Oppretter en ny instans av {@code SearchField} med spesifisert felt og søkeopsjon.
	 *
	 * @param field   Feltet som brukes i søket, kan være av hvilken som helst type.
	 * @param option  Søkeopsjonen som spesifiserer bruken av feltet i søkekonteksten.
	 */
		public SearchField(T field, SEARCHOPTION option) {
			this.field = field;
			this.option = option;
		}
	/**
	 * Henter feltet som er lagret i denne {@code SearchField}-instansen.
	 * Feltet kan være av hvilken som helst type som er definert av generisk type {@code T}.
	 *
	 * @return Feltet av typen {@code T} som er assosiert med denne søkefeltinstansen.
	 */
		public T getField() {
			return field;
		}
	/**
	 * Henter søkeopsjonen som er assosiert med dette feltet.
	 * Søkeopsjonen definerer hvordan feltet skal brukes i søkeprosessen.
	 *
	 * @return Søkeopsjonen som er assosiert med feltet.
	 */
		public SEARCHOPTION getOption() {
			return option;
		}
}

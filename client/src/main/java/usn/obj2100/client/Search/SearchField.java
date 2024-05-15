package usn.obj2100.client.Search;

public class SearchField<T> {
		private T field;
		private SEARCHOPTION option;

		public SearchField(T field, SEARCHOPTION option) {
			this.field = field;
			this.option = option;
		}

		public T getField() {
			return field;
		}

		public SEARCHOPTION getOption() {
			return option;
		}
}

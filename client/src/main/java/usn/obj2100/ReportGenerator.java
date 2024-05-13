package usn.obj2100;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGenerator {
	private InventarSearch inventarSearch;

	public ReportGenerator(DatabaseManager dbManager) {
		this.inventarSearch = new InventarSearch(dbManager);
	}

	public void generateReport(String criteria) {
		try {
			ResultSet rs = inventarSearch.searchInventar(criteria);
			while (rs.next()) {
				System.out.println("Kategori: " + rs.getString("kategori") + ", Beskrivelse: " + rs.getString("beskrivelse"));
			}
		} catch (SQLException e) {
			System.out.println("Database access error: " + e.getMessage());
		}
	}
}

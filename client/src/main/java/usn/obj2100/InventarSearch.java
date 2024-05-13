package usn.obj2100;

import java.sql.*;

public class InventarSearch {
	private DatabaseManager dbManager;

	public InventarSearch(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}

	// Moved from DatabaseManager class
	public ResultSet searchInventar(String query) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbManager.connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM inventar WHERE " + query);
			return rs;
		} catch (SQLException e) {
			System.err.println("Search error: " + e.getMessage());
			throw e;
		}
	}
}

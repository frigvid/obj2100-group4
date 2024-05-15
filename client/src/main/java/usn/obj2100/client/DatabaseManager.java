package usn.obj2100.client;

import java.sql.*;

public class DatabaseManager {
	private static final String DB_URL = "jdbc:sqlite:path_to_your_db.sqlite";

	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			System.err.println("Connection error: " + e.getMessage());
		}
		return conn;
	}

	public void insertInventar(InventarElement element) {
		// SQL-kommandoen inkluderer nå type og håndterer nullable levetid
		String sql = "INSERT INTO inventar(type, kategori, beskrivelse, innkjopsdato, innkjopspris, plassering, antall, levetid) VALUES(?,?,?,?,?,?,?,?)";
		try (Connection conn = this.connect();
			  PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, element.getType());
			pstmt.setString(2, element.getKategori());
			pstmt.setString(3, element.getBeskrivelse());
			pstmt.setString(4, element.getInnkjopsdato());
			pstmt.setDouble(5, element.getInnkjopspris());
			pstmt.setString(6, element.getPlassering());
			pstmt.setInt(7, element.getAntall());
			// Håndterer nullable integer for levetid
			if (element.getLevetid() != null) {
				pstmt.setInt(8, element.getLevetid());
			} else {
				pstmt.setNull(8, Types.INTEGER);
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Insert error: " + e.getMessage());
		}
	}

	// Overvei å implementere ytterligere metoder for oppdatering, sletting, og søk
}

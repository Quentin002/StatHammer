package controlleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupprListe {
	private BDD conec;

	public SupprListe() {
		this.conec = new BDD("400129", "stathammer_greta_admin", "stathammer_v1");
	}

	public void Suppression(int idListe) {

		String requeteSuppr = "DELETE FROM liste WHERE id_liste=?;";

		try (Connection conn = conec.getConnection(); PreparedStatement stmt = conn.prepareStatement(requeteSuppr)) {

			stmt.setInt(1, idListe);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

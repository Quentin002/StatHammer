package controlleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.ArmeeListe;

public class GestionListe {

	// Objet pour gérer la connexion à la bdd
	private BDD conec;

	// Constructeur de la clasee qui initialise la connexion à la bdd
	public GestionListe() {
		this.conec = new BDD();
	}

	// récupération de l'id utilisateur et les listes d'armée
	public ArrayList<ArmeeListe> getArmeeListe(int idUtilisateur) {
		// liste pour stocker les résultats
		ArrayList<ArmeeListe> listes = new ArrayList<>();

		// Requête pour récupérer les listes
		String requete = "SELECT id_liste, nom_liste, description_liste, data_liste, id_utilisateur FROM liste WHERE id_utilisateur = ?";
		try (Connection conn = conec.getConnection(); PreparedStatement stmt = conn.prepareStatement(requete)) {

			// remplace le ? par l'id utilisateur de idUtilisateur
			stmt.setInt(1, idUtilisateur);
			ResultSet recup = stmt.executeQuery(); // execution requête
			// Parcours des résultats pour les stocker dans la liste
			while (recup.next()) {
				int id = recup.getInt("id_liste");
				String nom = recup.getString("nom_liste");
				String description = recup.getString("description_liste");
				String data = recup.getString("data_liste");
				int utilisateurId = recup.getInt("id_utilisateur");

				listes.add(new ArmeeListe(id, nom, description, data, utilisateurId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listes;
	}
}

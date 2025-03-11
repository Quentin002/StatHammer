package controlleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import modele.ArmeeListe;

public class GestionListe {

	// Objet pour gérer la connexion à la bdd
	private BDD conec;

	// Constructeur de la clasee qui initialise la connexion à la bdd
	public GestionListe() {
		this.conec = new BDD("400129", "stathammer_greta_admin", "stathammer_v1");
	}

	// récupération de l'id utilisateur et les listes d'armée
	public ArrayList<ArmeeListe> getArmeeListe(int idUtilisateur) {
	    ArrayList<ArmeeListe> listes = new ArrayList<>();

	    // Requête pour récupérer les listes d'armée avec leurs unités associées
	    String requete = """
	        SELECT l.id_liste, l.nom_liste, l.description_liste, l.data_liste, u.nom_unite
	        FROM liste l
	        LEFT JOIN contenir c ON l.id_liste = c.id_liste
	        LEFT JOIN unite u ON c.id_unite = u.id_unite
	        WHERE l.id_utilisateur = ?
	    """;

	    try (Connection conn = conec.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(requete)) {

	        stmt.setInt(1, idUtilisateur);
	        ResultSet recup = stmt.executeQuery();

	        // Stocker les objets ArmeeListe par ID pour éviter les doublons
	        HashMap<Integer, ArmeeListe> mapListes = new HashMap<>();

	        while (recup.next()) {
	            int id = recup.getInt("id_liste");
	            String nom = recup.getString("nom_liste");
	            String description = recup.getString("description_liste");
	            String data = recup.getString("data_liste");
	            String nomUnite = recup.getString("nom_unite");

	            // Vérifier si la liste existe déjà dans la map
	            ArmeeListe liste = mapListes.get(id);
	            if (liste == null) {
	                liste = new ArmeeListe(id, nom, description, data);
	                liste.setUniteListe(new ArrayList<>()); // Initialiser la liste des unités
	                mapListes.put(id, liste);
	            }

	            // Ajouter l'unité si elle existe (éviter null)
	            if (nomUnite != null) {
	                liste.getUniteListe().add(nomUnite);
	            }
	        }

	        listes.addAll(mapListes.values());

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listes;
	}
}

package controlleur;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	    ArrayList<ArmeeListe> listes = new ArrayList<>();
	    // Requête pour récupérer les listes d'armée avec leurs unités associées
	    String requete = """
	        SELECT l.id_liste, l.nom_liste, l.description_liste, l.data_liste, u.nom_unite, u.id_armee
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
	        System.out.print(stmt + "côté controlleur");
	        while (recup.next()) {
	            int id = recup.getInt("id_liste");
	            String nom = recup.getString("nom_liste");
	            String description = recup.getString("description_liste");
	            String data = recup.getString("data_liste");
	            String nomUnite = recup.getString("nom_unite");
	            int idArmee = recup.getInt("id_armee");
	            // Vérifier si la liste existe déjà dans la map
	            ArmeeListe liste = mapListes.get(id);
	            if (liste == null) {
	                liste = new ArmeeListe(id, idArmee, nom, description, data, idArmee);
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
	
	public static void importer(Stage primaryStage, File file) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier de liste d'armée", "*.*"));
        file = fileChooser.showOpenDialog(primaryStage);
        
        // parsing du fichier
	}
}

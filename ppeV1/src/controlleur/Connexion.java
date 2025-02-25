package controlleur;

import java.util.ArrayList;

import javafx.stage.Stage;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;

public class Connexion {
	// Méthode qui vérifie les identifiants de connexion
	public static void verif(String login, String mdp,Stage primaryStage) {
		
		// Nettoyage pour supprimer les espaces
		login = login.trim();
		mdp = mdp.trim();
		// Connexion à la bdd
		BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
		
		// Récupération des info utilisateur depuis BDD
		ArrayList<Object> rendu = conec.selectUtilisateur(login, mdp);
		System.out.println(rendu.get(0)+" "+rendu.get(1));
		// Ferme la connexion
		conec.close();
		try {
			
		// Vérifie si le login stocké dans rendu correspond bien à celui de la bdd
		if (login.equals(rendu.get(1))) {
			int idUtilisateur = (int) rendu.get(0); // Récupère l'id utilisateur pour le stocker
			
			// Changement de page
			primaryStage.close();
			AfficheAccueil.affiche(primaryStage, idUtilisateur);
		}else {
			System.out.println(rendu);
		}
		}catch(Exception e) {
			// Affiche une page d'erreur
			AfficheConnexionFailed.affiche(primaryStage);
		}
		
	}
}

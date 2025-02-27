package controlleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.stage.Stage;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;
import modele.ModeleEvenement;

public class Connexion {
	public static void verif(String login, String mdp,Stage primaryStage) {
		
		login = login.trim();
		mdp = mdp.trim();
<<<<<<< HEAD
		
		
		try {
			
			BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
			
			ArrayList<String> rendu = conec.selectUtilisateur(login, mdp);
		
			
			String requete = "SELECT * FROM evenement WHERE id_evenement=?;";
			PreparedStatement stat = conec.getConnec().prepareStatement(requete);
			stat.setInt(1, 1);
			ResultSet rev = stat.executeQuery();
			while(rev.next()) {
				
				ModeleEvenement evenement = new ModeleEvenement(rev.getString("nom_evenement"),
																rev.getString("URL_evenement"),
																rev.getString("date_evenement"),
																rev.getString("description_evenement"));
			
				if (login.equals(rendu.getFirst().trim())) {
					
					primaryStage.close();
					AfficheAccueil.affiche(primaryStage, evenement);
				}
				else {
					System.out.println(rendu);
				}
			
			
			}
			
			
		
			
		
			conec.close();
=======
		BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
		
		ArrayList<String> rendu = conec.selectUtilisateur(login, mdp);
		int id = conec.UtilisateurID(login, mdp);
		String role = conec.UtilisateurRole(login, mdp);
		//System.out.println(role);
		conec.close();
		try {
			
		
		if (login.equals(rendu.getFirst().trim())) {
			User session = new User(login,id,role);
			//primaryStage.close();
			AfficheAccueil.affiche(primaryStage,session);
		}else {
			System.out.println(rendu);
		}
>>>>>>> main
		}catch(Exception e) {
			AfficheConnexionFailed.affiche(primaryStage);
		}
		
	}
}

package controlleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.stage.Stage;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;
import modele.ModeleEvenement;
import modele.User;

public class AdminController {
	public static void verif(String login, String mdp,Stage primaryStage) {
		
		
		login = login.trim();
		mdp = mdp.trim();
		
		
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
				
				User utilisateur = new User (rev.getString("nom"),
											rev.getString("email"),
											rev.getString("mdp"),
											rev.getString("role"));
			
				if (login.equals(rendu.getFirst().trim())) {
					
					primaryStage.close();
					AfficheAccueil.affiche(primaryStage, evenement);
				}
				else {
					System.out.println(rendu);
				}
			
			
			}
			
			
		
			
		
			conec.close();
		}catch(Exception e) {
			AfficheConnexionFailed.affiche(primaryStage);
		}
		
	}
}

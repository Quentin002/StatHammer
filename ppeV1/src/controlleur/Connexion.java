package controlleur;

import java.util.ArrayList;

import javafx.stage.Stage;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;

public class Connexion {
	public static void verif(String login, String mdp,Stage primaryStage) {
		
		login = login.trim();
		mdp = mdp.trim();
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
		}catch(Exception e) {
			AfficheConnexionFailed.affiche(primaryStage);
		}
		
	}
}

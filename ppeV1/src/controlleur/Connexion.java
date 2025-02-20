package controlleur;

import java.util.ArrayList;

import javafx.stage.Stage;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;

public class Connexion {
	public static void verif(String login, String mdp,Stage primaryStage) {
		
		
		login = login.trim();
		mdp = mdp.trim();
		BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
		
		ArrayList<String> rendu = conec.selectUtilisateur(login, mdp);
		conec.close();
		try {
			
		
		if (login.equals(rendu.getFirst().trim())) {
			
			primaryStage.close();
			AfficheAccueil.affiche(primaryStage);
		}else {
			System.out.println(rendu);
		}
		}catch(Exception e) {
			AfficheConnexionFailed.affiche(primaryStage);
		}
		
	}
}

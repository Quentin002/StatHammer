package controlleur;

import java.util.ArrayList;

import javafx.stage.Stage;
import vue.AfficheConnexionFailed;
import vue.AfficheLogged;

public class Connexion {
	public static void verif(String login, String mdp,Stage primaryStage) {
		
		
		
		BDD conec = new BDD("root","","StatHammer_v1");
		
		ArrayList<String> rendu = conec.selectUtilisateur(login, mdp);
		conec.close();
		try {
			
		
		if (login.equals(rendu.getFirst().trim())) {
			
			primaryStage.close();
			AfficheLogged.affiche(primaryStage);
		}else {
			System.out.println(rendu);
		}
		}catch(Exception e) {
			AfficheConnexionFailed.affiche(primaryStage);
		}
		
	}
}

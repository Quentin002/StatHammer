package controlleur;

import java.util.ArrayList;

import javafx.stage.Stage;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheConnexion;
import vue.AfficheConnexionFailed;

public class CreationCompte {
	public static void verif(String Email, String Pseudo, String Mdp, Stage primaryStage) {

		Email = Email.trim();
		Pseudo = Pseudo.trim();
		Mdp = Mdp.trim();
		BDD conec = new BDD();
		ArrayList<String> crea = conec.creerUtilisateur(Email, Pseudo, Mdp);
		int id = conec.UtilisateurID(Pseudo, Mdp);
		String role = conec.UtilisateurRole(Pseudo, Mdp);
		User session = new User(Pseudo,id,role);
		conec.close();
		if (!crea.isEmpty() && Email.equals(crea.getFirst().trim())) {

			System.out.println("Compté créé" + crea);
			primaryStage.close();
			AfficheAccueil.affiche(primaryStage, session);
		} else {
			System.out.println("échec de la création de compte" + crea);
			AfficheConnexionFailed.affiche(primaryStage);
		}
	}
	
	public static void getBack(Stage primaryStage) {
		AfficheConnexion.affiche(primaryStage);
	}

}

package controlleur;

import java.util.ArrayList;

import javafx.stage.Stage;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;

public class CreationCompte {
	public static void verif(String Email, String Pseudo, String Mdp, Stage primaryStage) {
	
		Email = Email.trim();
		Pseudo = Pseudo.trim();
		Mdp = Mdp.trim();
		BDD conec = new BDD("400129","stathammer_greta_admin","stathammer_v1");
		ArrayList<String> crea = conec.creerUtilisateur(Email, Pseudo,Mdp);
		conec.close();
			if (!crea.isEmpty()&& Email.equals(crea.getFirst().trim())) {
				
				System.out.println("Compté créé" + crea);
				primaryStage.close();
				AfficheAccueil.affiche(primaryStage);
			}else {
				System.out.println("échec de la création de compte" + crea);
				AfficheConnexionFailed.affiche(primaryStage);
			}}
			
		
}


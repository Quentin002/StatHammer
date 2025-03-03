package controlleur;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.stage.Stage;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;

public class Connexion {
	public static void verif(String login, String mdp,Stage primaryStage) {
		
		login = login.trim();
		mdp = mdp.trim();
		BDD.setInfos("400129","stathammer_greta_admin","stathammer_v1");
		BDD conec = new BDD();
		
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
			}
			else {
				System.out.println(rendu);
			}
		}
		catch(Exception e) {
			AfficheConnexionFailed.affiche(primaryStage);
		}
		
	}
	
	public static void updatePseudo(String pseudo,int id) throws SQLException {
		BDD conec = new BDD();
		conec.updateUtilisateur(pseudo,id);
		conec.close();
	}
	public static void updateMdp(String mdp,int id) throws SQLException {
		BDD conec = new BDD();
		conec.updateMp(mdp,id);
		conec.close();
	}
	public static String selectMdp(int id) throws SQLException {
		BDD conec = new BDD();
		String mdp =conec.UtilisateurMdp(id);
		conec.close();
		return mdp;
	}
}

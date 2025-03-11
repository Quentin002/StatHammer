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
		BDD conec = new BDD();
		
		ArrayList<Object> rendu = conec.selectUtilisateur(login, Integer.toString(mdp.hashCode()));
		int id = conec.UtilisateurID(login, Integer.toString(mdp.hashCode()));
		String role = conec.UtilisateurRole(login, Integer.toString(mdp.hashCode()));
		//System.out.println(role);
		conec.close();
		try {
			if (login.equals(rendu.get(0))) {
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
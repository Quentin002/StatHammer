package controlleur;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.stage.Stage;
import modele.Evenement;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheConnexionFailed;

public class Connexion {

	public static void verif(String login, String mdp,Stage primaryStage) {
		
		login = login.trim();
		mdp = Integer.toString(mdp.hashCode());
		BDD conec = new BDD();
		
		EvenementController eventC = new EvenementController();
    	Evenement evt = new Evenement(eventC.getNom_event().get(0),
				eventC.getNom_image().get(0),
				eventC.getDesc_event().get(0),
				eventC.getDate_event().get(0));
		System.out.println(eventC.getNom_image().get(0));
		ArrayList<Object> rendu = conec.selectUtilisateur(login, mdp);
		int id = conec.UtilisateurID(login, mdp);
		String role = conec.UtilisateurRole(login, mdp);
		//System.out.println(role);
		conec.close();
		
		try {
			if (login.equals(rendu.get(0))) {
				User session = new User(login,id,role);
				
				//primaryStage.close();
				AfficheAccueil.affiche(primaryStage,session,evt);
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
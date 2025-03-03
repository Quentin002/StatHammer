package controlleur;

import javafx.stage.Stage;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheAdmin;
import vue.AfficheConnexion;
import vue.AfficheGestionCompte;
import vue.simulation.AfficheSimulation;

public class ControllerTopMenu {
	static BDD db = new BDD();
	
	public static void go_accueil(Stage primaryStage,User session) { 
		//primaryStage.close(); pour fermer stage
		AfficheAccueil.affiche(primaryStage,session);
	}
	public static void go_gestion_compte(Stage primaryStage,User session) { 
		AfficheGestionCompte.affiche(primaryStage,session);
	}
	public static void go_gestion_listes(Stage primaryStage,User session) { 
		db.getArmyLists(session);
		//
	}
	public static void go_deco(Stage primaryStage) { 
		AfficheConnexion.affiche(primaryStage);
	}
	public static void go_simulaton(Stage primaryStage, User session)
	{
		db.getArmyLists(session);
		AfficheSimulation.affiche(primaryStage, session);
	}
	public static void go_admin(Stage primaryStage,User session) { 
		AfficheAdmin.affiche(primaryStage,session);
	}
}



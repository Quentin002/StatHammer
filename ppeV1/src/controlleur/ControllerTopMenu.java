package controlleur;

import javafx.stage.Stage;
import modele.Evenement;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheAdmin;
import vue.AfficheConnexion;
import vue.AfficheCreerListe;
import vue.AfficheGestionCompte;
import vue.GestionListe.AfficheGestionListe;
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

//		db.getArmyLists(session);
		AfficheGestionListe.affiche(primaryStage, session);

		//

	}
	public static void go_deco(Stage primaryStage, User session) { 
		AfficheSimulation.cleanWeaponsAtitudesMenu();
		AfficheSimulation.cleanBattle();
		session = null;
		AfficheConnexion.affiche(primaryStage);
	}
	public static void go_simulation(Stage primaryStage, User session){

		
		Instanciation.getArmyLists(session);

		AfficheSimulation.affiche(primaryStage, session);
	}
	public static void go_admin(Stage primaryStage,User session) { 
		AfficheAdmin.affiche(primaryStage,session);
	}
	public static void go_creer_liste(Stage primaryStage, User session) {
		AfficheCreerListe.afficheCreerListe(primaryStage, session);
	}
//	public static void go_gestionliste(Stage primaryStage,User session) { 
//		AfficheGestionListe.affiche(primaryStage,session);
//	}
}




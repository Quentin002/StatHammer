package controlleur;

import javafx.stage.Stage;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheConnexion;
import vue.AfficheGestionCompte;
import vue.GestionListe.AfficheGestionListe;
import vue.simulation.AfficheSimulation;

public class ControllerTopMenu {

	
	public static void go_acceuil(Stage primaryStage,User session) { 
		//primaryStage.close(); pour fermer stage
		AfficheAccueil.affiche(primaryStage,session);
	}
	public static void go_gestion_compte(Stage primaryStage,User session) { 
		AfficheGestionCompte.affiche(primaryStage,session);
	}
	public static void go_deco(Stage primaryStage) { 
		AfficheConnexion.affiche(primaryStage);
	}

	public static void go_simulation(Stage primaryStage,User session) { 
		AfficheSimulation.affiche(primaryStage,session);
	}
	public static void go_gestionliste(Stage primaryStage,User session) { 
		AfficheGestionListe.affiche(primaryStage,session);
	}
}




package controlleur;

import javafx.stage.Stage;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheConnexion;
import vue.AfficheGestionCompte;
import vue.simulation.AfficheSimulation;

public class ControllerTopMenu {

	
	public static void go_acceuil(Stage primaryStage,User session) { 
		primaryStage.close();
		AfficheAccueil.affiche(primaryStage,session);
	}
	public static void go_gestion_compte(Stage primaryStage,User session) { 
		primaryStage.close();
		AfficheGestionCompte.affiche(primaryStage,session);
	}
	public static void go_deco(Stage primaryStage) { 
		primaryStage.close();
		AfficheConnexion.affiche(primaryStage);
	}
	public static void go_simulaton(Stage primaryStage,User session) { 
		primaryStage.close();
		AfficheSimulation.affiche(primaryStage,session);
	}
}



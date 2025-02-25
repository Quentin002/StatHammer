package vue;

import controlleur.ControllerTopMenu;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modele.User;

public class afficheTopMenu extends HBox
{
	public afficheTopMenu(Stage primaryStage)
	{
		this.setStyle("-fx-background-color: gray; -fx-padding: 5px; -fx-spacing: 10px;");
		//Label title = new Label("Simulation"); // moche, à modifier
	    //Button btn_end = new Button("Fin simulation");
	    Button btn_account = new Button("Mon compte");
	    Button btn_logout = new Button("Déconnexion");
	    Button btn_home = new Button("Accueil");
	    Button btn_gerer_liste = new Button("Gerer liste");
	    Button btn_creer_liste = new Button("Création liste");
	    Button btn_simu = new Button("Simulation");
	    this.getChildren().addAll(btn_home, btn_account,btn_gerer_liste,btn_creer_liste,btn_simu, btn_logout);
	    this.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
	    
	    // vers le menu principal
	    btn_home.setOnAction(e -> {
			ControllerTopMenu.go_acceuil(primaryStage);
		});
	    // vers gestion compte utilisateur
	    btn_account.setOnAction(e -> {
	    	ControllerTopMenu.go_gestion_compte(primaryStage);
		});
	    // vers page de connexion
	    btn_logout.setOnAction(e -> {
	    	ControllerTopMenu.go_deco(primaryStage);
		});
	    btn_simu.setOnAction(e -> {
	    	ControllerTopMenu.go_simulaton(primaryStage);
		}); 
	}
}

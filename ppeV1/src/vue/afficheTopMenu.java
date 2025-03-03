package vue;

import controlleur.ControllerTopMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.User;

public class afficheTopMenu extends HBox
{
	private Label pseudo;
	
	public afficheTopMenu(Stage primaryStage, User session)
	{
		this.setStyle("-fx-background-color: gray; -fx-padding: 5px; -fx-spacing: 10px;");
		//Label title = new Label("Simulation"); // moche, à améliorer ou retirer
	    Button btn_account = new Button("Mon compte");
	    Button btn_logout = new Button("Déconnexion");
	    Button btn_home = new Button("Accueil");
	    Button btn_gerer_liste = new Button("Gerer liste");
	    Button btn_creer_liste = new Button("Création liste");
	    Button btn_admin = new Button("Admin");
	    Button btn_simu = new Button("Simulation");
	    this.getChildren().addAll(btn_home, btn_account,btn_gerer_liste,btn_creer_liste,btn_simu, btn_logout);
	    this.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
	    if(session.getRole().equals("Admin") ) {
	    	this.getChildren().add(btn_admin);
	    }
	    pseudo = new Label(session.getNom());
	    //pseudo.setStyle("-fx-text-fill: white");
	    pseudo.setTextFill(Color.WHITE);
	    pseudo.setStyle("-fx-font-weight: bold;");
	    this.getChildren().add(pseudo);
	    //scene.getStylesheets().add("styles.css");
	    
	    
	    // menu principal
	    btn_home.setOnAction(e -> {
			ControllerTopMenu.go_accueil(primaryStage,session);
		});
	    // gestion de son compte
	    btn_account.setOnAction(e -> {
	    	ControllerTopMenu.go_gestion_compte(primaryStage,session);
		});
		// gestion des listes
	    btn_gerer_liste.setOnAction(e -> {
	    	ControllerTopMenu.go_gestion_listes(primaryStage,session);
		});
	    // page de connexion
	    btn_logout.setOnAction(e -> {
	    	ControllerTopMenu.go_deco(primaryStage);
		});
		// simulation
	    btn_simu.setOnAction(e -> {
	    	ControllerTopMenu.go_simulaton(primaryStage,session);
		}); 
	    // options réservées à l'admin
	    btn_admin.setOnAction(e -> {
	    	ControllerTopMenu.go_admin(primaryStage, session);
		});
	}
	
	public void setLogin(String login)
	{
		pseudo.setText(login);
	}
}

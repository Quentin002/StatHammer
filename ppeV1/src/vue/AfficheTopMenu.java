package vue;

import controlleur.ControllerTopMenu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Evenement;
import modele.User;

public class AfficheTopMenu extends HBox
{
	private Label pseudo;
	
	public AfficheTopMenu(Stage primaryStage, User session)
	{
		this.setStyle("-fx-background-color: gray; -fx-padding: 5px;");
		//-fx-spacing: 10px;
		//Label title = new Label("Simulation"); // moche, à améliorer ou retirer
	    Button btn_account = new Button("Mon compte");
	    Button btn_logout = new Button();
	    Button btn_home = new Button("Accueil");
	    Button btn_gerer_liste = new Button("Gerer liste");
	    Button btn_creer_liste = new Button("Création liste");
	    Button btn_admin = new Button("Admin");
	    Button btn_simu = new Button("Simulation");
	    

	    HBox h1 = new HBox();
	    h1.getChildren().addAll(btn_home, btn_account,btn_gerer_liste,btn_creer_liste,btn_simu);

	   
	    
	    Image icons_deco = new Image("/images/deco.png");
		ImageView icons_deco_box = new ImageView(icons_deco);
		btn_logout.setGraphic(icons_deco_box);
		icons_deco_box.setPreserveRatio(true);
		icons_deco_box.fitHeightProperty().bind(btn_account.heightProperty());
		//this.getChildren().add(h1);
	    //this.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
	    //h1.setLeft(null);
	    //h2.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
	    //this.setSpacing(getMaxWidth());
	    //h1.setSpacing(10);// Ajoute un espace de 10 pixels entre les composants
		//h2.setPadding(new Insets(1, 1, 20, 1));
		
		
		
	    if(session.getRole().equals("Admin") ) {
	    	h1.getChildren().add(btn_admin);
	    }
	    pseudo = new Label(session.getNom());
	    //pseudo.setStyle("-fx-text-fill: white");
	    pseudo.setTextFill(Color.WHITE);
	    pseudo.setStyle("-fx-font-weight: bold;");
	    //barre.getChildren().addAll( pseudo, btn_logout);
	    

	    //this.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
	    HBox h2 = new HBox();
		h2.getChildren().addAll( pseudo, btn_logout);
		
		h1.setSpacing(10);
		h2.setSpacing(10);
		Pane p = new Pane();
		HBox.setHgrow(p, Priority.ALWAYS);
		this.getChildren().addAll( h1,p, h2);
		this.setAlignment(javafx.geometry.Pos.TOP_CENTER);
		h1.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
		h2.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
		
	    
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
	    	ControllerTopMenu.go_deco(primaryStage, session);
		});
		// simulation
	    btn_simu.setOnAction(e -> {
	    	ControllerTopMenu.go_simulation(primaryStage,session);
		}); 
	    
	    // options réservées à l'admin
	    btn_admin.setOnAction(e -> {
	    	ControllerTopMenu.go_admin(primaryStage, session);
		});
	    
	    //vers creer liste
	    btn_creer_liste.setOnAction(e->{
	    	ControllerTopMenu.go_creer_liste(primaryStage,session);
	    });

	}

	public void setLogin(String login)
	{
		pseudo.setText(login);
	}

}

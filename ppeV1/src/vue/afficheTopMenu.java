package vue;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class afficheTopMenu extends HBox
{
	public afficheTopMenu(Stage primaryStage)
	{
		this.setStyle("-fx-background-color: gray; -fx-padding: 5px; -fx-spacing: 10px;");
		//Label title = new Label("Simulation"); // moche, à modifier
	    Button btn_end = new Button("Fin simulation");
	    Button btn_account = new Button("Mon compte");
	    Button btn_logout = new Button("Déconnexion");
	    this.getChildren().addAll(btn_end, btn_account, btn_logout);
	    this.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
	    
	    // vers le menu principal
	    btn_end.setOnAction(e -> {
			primaryStage.close();
			AfficheAccueil.affiche(primaryStage);
		});
	    // vers gestion compte utilisateur
	    btn_account.setOnAction(e -> {
			//primaryStage.close();
			//AfficheGestionCompte.affiche(primaryStage);
		});
	    // vers page de connexion
	    btn_logout.setOnAction(e -> {
			primaryStage.close();
			AfficheConnexion.affiche(primaryStage);
		});
	}
}

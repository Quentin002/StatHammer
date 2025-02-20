package vue;

import java.awt.Dimension;

import controlleur.Connexion;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficheConnexion {
	public static void affiche(Stage primaryStage) {
		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight()/2;
		double largeur = tailleEcran.getWidth()/2;
		
		VBox root = new VBox();
		Scene scene = new Scene(root,largeur,hauteur);		
		TextField pseudo = new TextField();
		PasswordField mdp = new PasswordField();
		Label nomPseudo = new Label("Pseudo");
		Label nomMdp = new Label("Mot de passe");
		Button envoi = new Button("Appuyer sur moi");
		
		root.getChildren().add(nomPseudo);
		root.getChildren().add(pseudo);
		root.getChildren().add(nomMdp);
		root.getChildren().add(mdp);
		root.getChildren().add(envoi);
		
		mdp.setMaxWidth(200);
		pseudo.setMaxWidth(200);
		
		pseudo.setAlignment(Pos.CENTER);
		mdp.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);
		
		envoi.setOnAction(e ->{
			
			Connexion.verif(pseudo.getText(),mdp.getText(),primaryStage);
		});
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

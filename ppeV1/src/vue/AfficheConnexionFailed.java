package vue;

import controlleur.Connexion;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AfficheConnexionFailed {
	public static void affiche(Stage primaryStage) {
		VBox root = new VBox();
		Scene scene = new Scene(root, 800, 600);
		TextField pseudo = new TextField();
		TextField mdp = new TextField();
		Label nomPseudo = new Label("Pseudo");
		Label nomMdp = new Label("Mot de passe");
		Button envoi = new Button("Connexion");
		Button creaCompte = new Button("Créer un compte");
		Label erreur = new Label("Mauvais Login et/ou MDP");

		erreur.setTextFill(Color.RED);
		erreur.setAlignment(Pos.CENTER);

		root.getChildren().add(nomPseudo);
		root.getChildren().add(pseudo);
		root.getChildren().add(nomMdp);
		root.getChildren().add(mdp);
		root.getChildren().add(envoi);
		root.getChildren().add(creaCompte);
		root.getChildren().add(erreur);

		mdp.setMaxWidth(200);
		pseudo.setMaxWidth(200);

		pseudo.setAlignment(Pos.CENTER);
		mdp.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);

		envoi.setOnAction(e -> {

			Connexion.verif(pseudo.getText(), mdp.getText(), primaryStage);
		});
		creaCompte.setOnAction(e -> {
			AfficheCreationCompte.affiche(primaryStage);
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

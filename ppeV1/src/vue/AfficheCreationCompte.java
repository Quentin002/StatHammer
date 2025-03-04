package vue;

import controlleur.CreationCompte;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficheCreationCompte {
	public static void affiche(Stage primaryStage) {
		VBox root = new VBox();
		Scene scene = new Scene(root, 800, 600);
		TextField Email = new TextField();
		TextField Pseudo = new TextField();
		TextField Mdp = new TextField();
		Label nomEmail = new Label("Email");
		Label nomPseudo = new Label("Pseudonyme");
		Label nomMdp = new Label("Mot de passe");
		Button envoi = new Button("Créer un compte");
		Button getback = new Button("Retour page de connexion");

		root.getChildren().add(nomEmail);
		root.getChildren().add(Email);
		root.getChildren().add(nomPseudo);
		root.getChildren().add(Pseudo);
		root.getChildren().add(nomMdp);
		root.getChildren().add(Mdp);
		root.getChildren().add(envoi);
		root.getChildren().add(getback);

		Email.setMaxWidth(200);
		Pseudo.setMaxWidth(200);
		Mdp.setMaxWidth(200);

		Email.setAlignment(Pos.CENTER);
		Pseudo.setAlignment(Pos.CENTER);
		Mdp.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);

		envoi.setOnAction(e -> {

			CreationCompte.verif(Email.getText(), Pseudo.getText(), Mdp.getText(), primaryStage);
		});
		getback.setOnAction(e -> {

			CreationCompte.getBack(primaryStage);
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

package vue;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Connexion {
	public static void affiche(VBox root) {
		TextField pseudo = new TextField();
		TextField mdp = new TextField();
		Label nomPseudo = new Label("Pseudo");
		Label nomMdp = new Label("Mot de passe");
		root.getChildren().add(nomPseudo);
		root.getChildren().add(pseudo);
		root.getChildren().add(nomMdp);
		root.getChildren().add(mdp);
		mdp.setMaxWidth(200);
		pseudo.setMaxWidth(200);
		pseudo.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);
		
	}
}

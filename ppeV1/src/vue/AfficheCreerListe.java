package vue;

import java.awt.Dimension;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficheCreerListe {
	public static void afficheCreerListe(Stage primaryStage) {
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight()/2;
		double largeur = tailleEcran.getWidth()/2;
		
		
		VBox root = new VBox();
		Scene scene = new Scene(root,largeur,hauteur);
		Label titre = new Label("StatHammer");
		
		
		root.getChildren().add(titre);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

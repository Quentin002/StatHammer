package vue;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficheLogged {
	public static void affiche(Stage primaryStage) {
		VBox root = new VBox();
		Scene scene = new Scene(root,1200,800);
		
		Label titre = new Label("StatHammer");
		VBox cadre = new VBox();
		Button simulation = new Button("SIMULATION");
		Button creeListe = new Button("Creer Liste");
		Button gererListe = new Button("Gerer Liste");
		
		root.getChildren().add(titre);
		root.getChildren().add(cadre);
		cadre.setAlignment(Pos.CENTER_RIGHT);
		cadre.getChildren().add(simulation);
		cadre.getChildren().add(creeListe);
		cadre.getChildren().add(gererListe);
		root.setAlignment(Pos.TOP_CENTER);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

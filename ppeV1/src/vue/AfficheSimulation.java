package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AfficheSimulation {
	public static void affiche(Stage primaryStage) {
		VBox root = new VBox();
		Scene scene = new Scene(root,1200,800);
		Image wip = new Image("/images/wip.jpg");
		ImageView iv1 = new ImageView();
		iv1.setImage(wip);
		iv1.setFitWidth(900);
		iv1.setPreserveRatio(true);
		iv1.setSmooth(true);
		iv1.setCache(true);
		
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(iv1);
		root.setBackground(Background.fill(Color.BLACK));
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("SIMULATION");
		primaryStage.show();
		
	}
}

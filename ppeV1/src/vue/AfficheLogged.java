package vue;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AfficheLogged {
	public static void affiche(Stage primaryStage) {
		VBox root = new VBox();
		Scene scene = new Scene(root,1200,800);
		Rectangle rect = new Rectangle(50,50,Color.BLUE);
		
		
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(rect);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import vue.Connexion;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Connexion.affiche(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package application;

import javafx.application.Application;
import javafx.stage.Stage;
import vue.AfficheConnexion;

public class Main extends Application {

	// le main appelle la vue de connexion
	@Override
	public void start(Stage primaryStage) {
		try {

			AfficheConnexion.affiche(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}

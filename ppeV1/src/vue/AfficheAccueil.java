package vue;

import vue.simulation.AfficheSimulation;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.User;

public class AfficheAccueil {
	public static void affiche(Stage primaryStage,User session) {
		VBox root = new VBox();
		Scene scene = new Scene(root,800,600);
	
		AfficheTopMenu menu = new AfficheTopMenu(primaryStage,session);

		root.getChildren().add(menu);
		
		Label titre = new Label("StatHammer");
		VBox cadre = new VBox();
		VBox cadreImage = new VBox();
		HBox capsule = new HBox();
		
		Button simulation = new Button("SIMULATION");
		Button creeListe = new Button("CREER LISTE");
		Button gererListe = new Button("GERER LISTE");
		
		Image accueil = new Image("/images/accueil.jpg");
		ImageView iv1 = new ImageView();
		iv1.setImage(accueil);
		iv1.setFitWidth(700);
		iv1.setPreserveRatio(true);
		iv1.setSmooth(true);
		iv1.setCache(true);
		
		
		
		
		titre.setFont(Font.font(32));
		titre.setPrefHeight(150);
		
		creeListe.setPrefWidth(200);
		creeListe.setPrefHeight(50);
		
		simulation.setPrefWidth(200);
		simulation.setPrefHeight(50);
		
		gererListe.setPrefWidth(200);
		gererListe.setPrefHeight(50);
		
		root.getChildren().add(titre);
		root.getChildren().add(capsule);
		
		capsule.getChildren().add(cadreImage);
		capsule.getChildren().add(cadre);
		
		
		cadre.setPrefSize(300, 800);
		cadre.setAlignment(Pos.CENTER);
		
		
		cadreImage.setPrefSize(900, 800);
		cadreImage.setAlignment(Pos.CENTER);
		
		cadreImage.getChildren().add(iv1);
		/*cadre.getChildren().add(simulation);
		cadre.getChildren().add(creeListe);
		cadre.getChildren().add(gererListe);
		
		
		
		
		
		simulation.setOnAction(e -> {
			primaryStage.close();
			AfficheSimulation.affiche(primaryStage);
			
		});
		*/
		root.setAlignment(Pos.TOP_CENTER);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
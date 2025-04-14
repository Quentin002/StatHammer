package vue;

import controlleur.Connexion;
import controlleur.EvenementController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Evenement;
import modele.User;

public class AfficheEvenement {
	//private static Evenement event;

	public static VBox NewAfficheEvenement(Stage primaryStage, User session) {
		
		VBox main = new VBox();
		main.setSpacing(20); // Espacement entre les éléments
		main.setStyle("-fx-padding: 20; -fx-alignment: center;"); // Ajout de marges autour et centrage du VBox
		
		HBox hBouton = new HBox();
		hBouton.setSpacing(10); // Espacement entre les boutons
		hBouton.setStyle("-fx-alignment: center;"); // Alignement des boutons au centre
		
		// Style gothique pour le nom de l'événement
		Label nomEvt = new Label(Connexion.event.getNom_evenement());
		nomEvt.setStyle("-fx-font-size: 32px; -fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-text-fill: #2F4F4F; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 5, 2, 2);");
		nomEvt.setStyle("-fx-font-size: 32px; -fx-font-family: 'Garamond'; -fx-font-weight: bold; -fx-text-fill: #2F4F4F; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.6), 15, 0.5, 0, 3);");
		
		// Style de la date et de la description
		Label dateEvt = new Label(Connexion.event.getData_evenement());
		dateEvt.setStyle("-fx-font-size: 16px; -fx-text-fill: #555555;"); // Style de la date de l'événement
		
		Label descEvt = new Label(Connexion.event.getDescritption_evenement());
		descEvt.setStyle("-fx-font-size: 14px; -fx-text-fill: #777777; -fx-wrap-text: true;");
		descEvt.setMaxWidth(400); // Limite la largeur de la description
		
		HBox hLabel = new HBox();
		hLabel.setSpacing(10);
		hLabel.getChildren().add(dateEvt);
		hLabel.getChildren().add(descEvt);
		hLabel.setStyle("-fx-alignment: center;"); // Alignement des labels au centre

		// Ajout des boutons dans le HBox avec style "dark fantasy" modeste
	    for (int i = 0; i < Connexion.eventC.getNbrEvt(); i++) {
	    	int j = i;
	    	Button btn = new Button(Connexion.eventC.getNom_event().get(i));
		    btn.setStyle("-fx-background-color: #1a1a1a; -fx-text-fill: #e6e6e6; -fx-font-size: 14px; -fx-padding: 10px 15px; -fx-border-radius: 5px; -fx-border-color: #444444; -fx-border-width: 1px;");
		    btn.setOnAction(e -> {
		    	Connexion.event = new Evenement(Connexion.eventC.getNom_event().get(j),
						Connexion.eventC.getNom_image().get(j),
						Connexion.eventC.getDesc_event().get(j),
						Connexion.eventC.getDate_event().get(j));
				
				AfficheAccueil.affiche(primaryStage, session);
			});
	        hBouton.getChildren().add(btn);
	      }

		// Image avec centrage et un peu plus de style sombre
		Image imageAccueil = new Image("images/" + Connexion.event.getNom_image());
		ImageView iv1 = new ImageView();
		iv1.setImage(imageAccueil);
		iv1.setFitWidth(500);
		iv1.setPreserveRatio(true);
		iv1.setSmooth(true);
		iv1.setCache(true);
		iv1.setStyle("-fx-border-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 15, 3, 2, 2);");

		// Centrer le nom et l'image dans un VBox intermédiaire
		VBox centeredContent = new VBox();
		centeredContent.setSpacing(10);
		centeredContent.setStyle("-fx-alignment: center;");
		centeredContent.getChildren().add(nomEvt);
		centeredContent.getChildren().add(iv1);

		// Ajout des éléments dans le VBox principal
		main.getChildren().add(centeredContent); // Ajout du nom et de l'image centrés
		main.getChildren().add(hBouton); // Ajout des boutons
		main.getChildren().add(hLabel); // Ajout de la date et description

		// Retourne le VBox stylisé
		return main;
	}
}

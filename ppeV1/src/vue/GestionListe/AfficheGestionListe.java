package vue.GestionListe;

import controlleur.GestionListe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.ArmeeListe;
import modele.User;
import vue.AfficheAccueil;
import vue.AfficheTopMenu;
import controlleur.SupprListe;

import java.util.ArrayList;

public class AfficheGestionListe {
	public static void affiche(Stage primaryStage, User session) {
		// Liste des conteneurs principaux structurant l'interface
		VBox root = new VBox(); // page principa
		HBox boite = new HBox(); // Box contenant les parties bouton et liste
		VBox selectBouton = new VBox(); // Partie bouton
		VBox liste = new VBox(); // Partie Liste
		Scene scene = new Scene(root, 1000, 800); // taille de la scène

		AfficheTopMenu menu = new AfficheTopMenu(primaryStage,session);

		root.getChildren().add(menu);

		// _______________ BOX BOITE _______________
		// Partie bouton
		Button Retour = new Button("Retour");
		Button Import = new Button("Importer une liste");
		// dimension des boutons
		Retour.setPrefSize(200, 50);
		Import.setPrefSize(200, 50);
		// configuration des boutons
		selectBouton.setPadding(new Insets(50, 0, 0, 0));
		selectBouton.setAlignment(Pos.TOP_CENTER);
		selectBouton.getChildren().addAll(Retour, Import);
		selectBouton.setPrefWidth(400);
		selectBouton.setBackground(Background.fill(Color.LIGHTGRAY));

		// Conteneur des listes
		liste.setPrefWidth(800);
		liste.setBackground(Background.fill(Color.LIGHTGREY));
		liste.setSpacing(10);
		liste.setStyle("-fx-border-color: black; -fx-border-width: 1px;"); // bordure du conteneur

		// Charger les listes de l'utilisateur
		GestionListe gestionListe = new GestionListe();
		ArrayList<ArmeeListe> listes = gestionListe.getArmeeListe(session.getId()); // récupération des listes avec l'id
																					// utilisateur

		// Boucle pour afficher les listes
		for (ArmeeListe armee : listes) {
			VBox listeBox = new VBox();
			listeBox.setPadding(new Insets(10));
			listeBox.setSpacing(5);
			listeBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

			Label nomListe = new Label(armee.getNomListe());
			Label descriptionListe = new Label(armee.getDescriptionListe());
			VBox uniteBox = new VBox();
		    for (String unite : armee.getUniteListe()) {
		        Label nomUnite = new Label("- " + unite);
		        uniteBox.getChildren().add(nomUnite);
		    }

			// Ajout des boutons (Partage, Modification, Suppression)
			HBox actions = new HBox();
			actions.setSpacing(10);
			Button partageBtn = new Button("P");
			Button modifBtn = new Button("Para");
			Button supprBtn = new Button("X");

			actions.getChildren().addAll(partageBtn, modifBtn, supprBtn);
			actions.setAlignment(Pos.CENTER_RIGHT);

			listeBox.getChildren().addAll(nomListe, descriptionListe, uniteBox, actions);
			liste.getChildren().add(listeBox);
			
			modifBtn.setOnAction(e ->{
				primaryStage.close();
				AfficheModificationListe.affiche(primaryStage, session, armee.getIdListe(), armee.getIdArmee());
			});

			supprBtn.setOnAction(e -> {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
						"Voulez-vous vraiment supprimer la liste :" + armee.getNomListe() + "?", ButtonType.YES,
						ButtonType.NO);
				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.YES) {
						int idListe = armee.getIdListe();
						SupprListe suppr = new SupprListe();
						suppr.Suppression(idListe);

						liste.getChildren().remove(listeBox);
					}
				});
			});
		}
		// ajout des conteneurs principaux à la scène
		boite.getChildren().addAll(selectBouton, liste);
		root.getChildren().addAll(boite);

		// Bouton Retour
		Retour.setOnAction(e -> {
			primaryStage.close();
			AfficheAccueil.affiche(primaryStage, session);
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

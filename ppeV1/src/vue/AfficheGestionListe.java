package vue;

import controlleur.GestionListe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.ArmeeListe;

import java.util.ArrayList;

public class AfficheGestionListe {
    public static void affiche(Stage primaryStage, int idUtilisateur) {
    	// Liste des conteneurs principaux structurant l'interface
        VBox root = new VBox(); //page principa
        HBox header = new HBox(); // le header
        HBox boite = new HBox(); // Box contenant les parties bouton et liste
        VBox selectBouton = new VBox(); // Partie bouton
        VBox liste = new VBox(); // Partie Liste
        Scene scene = new Scene(root, 1200, 800); //taille de la scène

        // _______________ HEADER _______________
        	// Création et configuration du titre
        Label titre = new Label("StatHammer");
        header.setAlignment(Pos.CENTER);
        header.setPrefHeight(100);

        // _______________ BOX BOITE _______________
        	// Partie bouton
        Button Retour = new Button("Retour");
        Button Import = new Button("Importer une liste");
        	// dimension des boutons
        Retour.setPrefSize(200, 50);
        Import.setPrefSize(200, 50);
        	//configuration des boutons
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
        ArrayList<ArmeeListe> listes = gestionListe.getArmeeListe(idUtilisateur); // récupération des listes avec l'id utilisateur
        
        	// Boucle pour afficher les listes
        for (ArmeeListe armee : listes) {
            VBox listeBox = new VBox();
            listeBox.setPadding(new Insets(10));
            listeBox.setSpacing(5);
            listeBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");


            Label nomListe = new Label(armee.getNom());
            Label descriptionListe = new Label(armee.getDescription());
            
            // Ajout des boutons (Partage, Modification, Suppression)
            HBox actions = new HBox();
            actions.setSpacing(10);
            Button partageBtn = new Button("P");
            Button modifBtn = new Button("Para");
            Button supprBtn = new Button("X");
            
            
            actions.getChildren().addAll(partageBtn, modifBtn, supprBtn);
            actions.setAlignment(Pos.CENTER_RIGHT);

            listeBox.getChildren().addAll(nomListe, descriptionListe, actions);
            liste.getChildren().add(listeBox);
        }
        // ajout des conteneurs principaux à la scène
        boite.getChildren().addAll(selectBouton, liste);
        root.getChildren().addAll(titre, header, boite);

        // Bouton Retour
        Retour.setOnAction(e -> {
            primaryStage.close();
            AfficheAccueil.affiche(primaryStage, idUtilisateur);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

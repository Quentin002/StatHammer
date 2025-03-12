package vue.GestionListe;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.ArmeeListe;
import modele.Figurine;
import modele.User;
import vue.AfficheTopMenu;

import java.util.ArrayList;

import controlleur.ModificationListe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class AfficheModificationListe {
    public static void affiche(Stage primaryStage, User session, int idListe) {
        VBox root = new VBox();
        HBox boite = new HBox();
        VBox afficheListe = new VBox();
        ScrollPane modifListe = new ScrollPane();
        VBox modif = new VBox();
        Scene scene = new Scene(root, 800, 600);
        AfficheTopMenu menu = new AfficheTopMenu(primaryStage, session);

        root.getChildren().add(menu);

        ModificationListe modificationListe = new ModificationListe();
        ArrayList<ArmeeListe> listes = modificationListe.getArmeeListe(idListe);

        // Colonne Affichage de la liste
        for (ArmeeListe armee : listes) {
            VBox listeBox = new VBox();
            listeBox.setPadding(new Insets(10));
            listeBox.setSpacing(5);
            listeBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

            Label nomListe = new Label(armee.getNomListe());
            VBox uniteBox = new VBox();
            for (String unite : armee.getUniteListe()) { //boucle les noms des unités
                Label nomUnite = new Label("| " + unite);
                uniteBox.getChildren().add(nomUnite);

                // Récupérer et afficher les figurines pour cette unité
                ArrayList<Figurine> figurines = modificationListe.getFigurineListe(idListe, unite);
                for (Figurine figurine : figurines) {
                    Label nomFigurine = new Label("	  - " + figurine.getNom());
                    uniteBox.getChildren().add(nomFigurine);
                }
            }
            listeBox.getChildren().addAll(nomListe, uniteBox);
            afficheListe.getChildren().add(listeBox);
        }

        afficheListe.setPrefWidth(200);
        afficheListe.setPrefHeight(500);
        afficheListe.setBackground(Background.fill(Color.LIGHTGREY));

        // Colonne ajout des unités.
        modif.setPrefWidth(200);
        modif.setBackground(Background.fill(Color.LIGHTGREY));
        modif.setPrefHeight(500);

        modifListe.setContent(modif);
        boite.getChildren().addAll(modifListe, afficheListe);
        root.getChildren().addAll(boite);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

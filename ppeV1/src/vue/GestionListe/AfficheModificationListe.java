package vue.GestionListe;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.ArmeeListe;
import modele.Figurine;
import modele.User;
import vue.afficheTopMenu;

import java.util.ArrayList;

import controlleur.GestionListe;
import controlleur.ModificationListe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class AfficheModificationListe {
    public static void affiche(Stage primaryStage, User session, int idListe, int idArmee) {
        VBox root = new VBox();
        HBox boite = new HBox();
        VBox afficheListe = new VBox();
        ScrollPane scrollModifListe = new ScrollPane();
        ScrollPane scrollAfficheListe = new ScrollPane();
        VBox unitDispo = new VBox();
        Scene scene = new Scene(root, 1200, 600);
        afficheTopMenu menu = new afficheTopMenu(primaryStage, session);

        root.getChildren().add(menu);

        ModificationListe modificationListe = new ModificationListe();
        ArrayList<ArmeeListe> listes = modificationListe.getArmeeListe(idListe);

        // Colonne Affichage de la liste principale
        for (ArmeeListe armee : listes) {
            VBox listeBox = new VBox();
            listeBox.setPadding(new Insets(10));
            listeBox.setSpacing(5);
            listeBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

            Label nomListe = new Label(armee.getNomListe());
            VBox uniteBox = new VBox();
            for (String unite : armee.getUniteListe()) {
                Label nomUnite = new Label("| " + unite);
                uniteBox.getChildren().add(nomUnite);

                // Récupérer et afficher les figurines pour cette unité
                ArrayList<Figurine> figurines = modificationListe.getFigurineListe(idListe, unite);
                for (Figurine figurine : figurines) {
                    Label nomFigurine = new Label("   - " + figurine.getNom());
                    uniteBox.getChildren().add(nomFigurine);
                }

            }
            listeBox.getChildren().addAll(nomListe, uniteBox);
            afficheListe.getChildren().add(listeBox);
        }

        afficheListe.setPrefWidth(400);
        afficheListe.setPrefHeight(500);
        afficheListe.setBackground(Background.fill(Color.LIGHTGREY));

        // Colonne des unités disponibles
        ArrayList<ArmeeListe> toutesUnites = modificationListe.getAllUnites(idArmee);
        for (ArmeeListe armee : toutesUnites) {
            VBox listeBox = new VBox();
            listeBox.setPadding(new Insets(10));
            listeBox.setSpacing(5);
            listeBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

            Label nomListe = new Label(armee.getNomListe());
            VBox uniteBox = new VBox();
            for (String unite : armee.getUniteListe()) {
            	Button ajoutUnit = new Button("ajouter");
            	uniteBox.getChildren().add(ajoutUnit);
                // Récupérer et afficher les figurines pour cette unité
                ArrayList<Figurine> figurines = modificationListe.getAllFigurineListe(idArmee, unite);
                for (Figurine figurine : figurines) {
                	
                    Label nomFigurine = new Label("   - " + figurine.getNom());
                    uniteBox.getChildren().add(nomFigurine);
                }

            }
            listeBox.getChildren().addAll(nomListe, uniteBox);
            unitDispo.getChildren().add(listeBox);
        }

        unitDispo.setPrefWidth(400);
        unitDispo.setPrefHeight(500);
        unitDispo.setBackground(Background.fill(Color.LIGHTGREY));
        scrollModifListe.setContent(unitDispo);
        scrollAfficheListe.setContent(afficheListe);

        // Bouton Valider
        Button valider = new Button("Valider");
        valider.setOnAction(e -> {
            // Enregistrer les modifications dans la base de données
            modificationListe.validerModifications(idListe, listes);
        });

        

        boite.getChildren().addAll( scrollModifListe, scrollAfficheListe, valider);
        root.getChildren().addAll(boite);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

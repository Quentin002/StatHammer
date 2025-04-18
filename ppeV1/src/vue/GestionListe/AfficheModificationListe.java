package vue.GestionListe;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.ArmeeListe;
import modele.Evenement;
import modele.Figurine;
import modele.User;
import vue.AfficheTopMenu;

import java.util.ArrayList;

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

        AfficheTopMenu menu = new AfficheTopMenu(primaryStage, session);

        root.getChildren().add(menu);

        ModificationListe modificationListe = new ModificationListe();
        ArrayList<ArmeeListe> listes = modificationListe.getArmeeListe(idListe);

        // Listes temporaires pour les unités ajoutées et supprimées
        ArrayList<String> unitesAjoutees = new ArrayList<>();
        ArrayList<String> unitesSupprimees = new ArrayList<>();

        // Colonne Affichage de la liste principale
        for (ArmeeListe armee : listes) {
            VBox listeBox = new VBox();
            listeBox.setPadding(new Insets(10));
            listeBox.setSpacing(5);
            listeBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

            Label nomListe = new Label(armee.getNomListe());
            VBox uniteBox = new VBox();
            for (String unite : armee.getUniteListe()) {
                VBox uniteContainer = new VBox();

                Button retirerUnit = new Button("retirer");
                uniteContainer.getChildren().add(retirerUnit);
                retirerUnit.setOnAction(e -> {
                    if (!unitesSupprimees.contains(unite)) {
                        unitesSupprimees.add(unite);
                        uniteBox.getChildren().remove(uniteContainer);
                        System.out.println("Unité retirée : " + unite);
                    }
                });

                Label nomUnite = new Label("| " + unite);
                uniteContainer.getChildren().add(nomUnite);

                // Récupérer et afficher les figurines pour cette unité
                ArrayList<Figurine> figurines = modificationListe.getFigurineListe(idListe, unite);
                for (Figurine figurine : figurines) {
                    Label nomFigurine = new Label("   - " + figurine.getNom());
                    uniteContainer.getChildren().add(nomFigurine);
                }

                uniteBox.getChildren().add(uniteContainer);
            }
            listeBox.getChildren().addAll(nomListe, uniteBox);
            afficheListe.getChildren().add(listeBox);
        }

        afficheListe.setPrefWidth(400);
        afficheListe.setPrefHeight(500);
        afficheListe.setBackground(Background.fill(Color.LIGHTGREY));

        // Colonne des unités disponibles
        ArrayList<ArmeeListe> toutesUnites = modificationListe.getAllUnites(idArmee, idListe);
        for (ArmeeListe armee : toutesUnites) {
            VBox listeBox = new VBox();
            listeBox.setPadding(new Insets(10));
            listeBox.setSpacing(5);
            listeBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

            VBox uniteBox = new VBox();
            for (String unite : armee.getUniteListe()) {
                Button ajouterUnit = new Button("ajouter");
                uniteBox.getChildren().add(ajouterUnit);
                ajouterUnit.setOnAction(e -> {
                    if (!unitesAjoutees.contains(unite)) {
                        unitesAjoutees.add(unite);

                        // Ajouter l'unité et ses figurines à l'affichage
                        VBox nouvelleUniteContainer = new VBox();

                        Button nouveauRetirerUnit = new Button("retirer");
                        nouvelleUniteContainer.getChildren().add(nouveauRetirerUnit);
                        nouveauRetirerUnit.setOnAction(event -> {
                            if (!unitesSupprimees.contains(unite)) {
                                unitesSupprimees.add(unite);
                                afficheListe.getChildren().remove(nouvelleUniteContainer);
                                System.out.println("Unité retirée : " + unite);
                            }
                        });

                        Label nouvelleUnite = new Label("| " + unite);
                        nouvelleUniteContainer.getChildren().add(nouvelleUnite);

                        // Récupère et affiche les figurines de unité
                        ArrayList<Figurine> figurines = modificationListe.getAllFigurineListe(idArmee, unite);
                        for (Figurine figurine : figurines) {
                            Label nomFigurine = new Label("   - " + figurine.getNom());
                            nouvelleUniteContainer.getChildren().add(nomFigurine);
                        }

                        afficheListe.getChildren().add(nouvelleUniteContainer);
                        System.out.println("Unité ajoutée : " + unite);
                    }
                });

                Label nomUnite = new Label("| " + unite);
                uniteBox.getChildren().add(nomUnite);

                // Récupérer et afficher les figurines de l'unité
                ArrayList<Figurine> figurines = modificationListe.getAllFigurineListe(idArmee, unite);
                for (Figurine figurine : figurines) {
                    Label nomFigurine = new Label("   - " + figurine.getNom());
                    uniteBox.getChildren().add(nomFigurine);
                }
            }
            listeBox.getChildren().addAll(uniteBox);
            unitDispo.getChildren().add(listeBox);
        }

        unitDispo.setPrefWidth(400);
        unitDispo.setPrefHeight(500);
        unitDispo.setBackground(Background.fill(Color.LIGHTGREY));
        scrollModifListe.setContent(unitDispo);
        scrollAfficheListe.setContent(afficheListe);

        Button valider = new Button("Valider");
        valider.setOnAction(e -> {
            // Envoyer les modifications  à la bdd
            modificationListe.ajouterUnites(idListe, unitesAjoutees);
            modificationListe.supprimerUnites(idListe, unitesSupprimees);
            System.out.println("Modifications validées.");
        });

        boite.getChildren().addAll(scrollModifListe, scrollAfficheListe, valider);
        root.getChildren().addAll(boite);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

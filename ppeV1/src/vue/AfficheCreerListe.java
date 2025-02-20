package vue;

import java.awt.Dimension;
import java.util.ArrayList;

import controlleur.Instanciation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import modele.Armee;
import modele.Faction;

public class AfficheCreerListe {
	public static void afficheCreerListe(Stage primaryStage) {
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight()/2;
		double largeur = tailleEcran.getWidth()/2;
		
		String defaut = new String();
		
		VBox root = new VBox();
		HBox boite = new HBox();
		VBox gauche = new VBox();
		VBox droite = new VBox();
		HBox entete = new HBox();
		VBox gaucheCorps = new VBox();
		HBox corpTete = new HBox();
		Scene scene = new Scene(root,largeur,hauteur);
		Label titre = new Label("StatHammer");
		Image logoFaction = new Image("images/wip.jpg");
		ImageView iv1 = new ImageView(logoFaction);
		ImageView iv2 = new ImageView(logoFaction);
		Button creation = new Button("Créer une unité");
		ChoiceBox<Faction> faction = new ChoiceBox<>();
		
		
		for(Faction fac:Instanciation.getFaction()) {
			faction.getItems().add(fac);
		}
		faction.setValue(faction.getItems().getFirst());
		
		
		
		
		ChoiceBox<Armee> groupe = new ChoiceBox<>();
		for(Armee armee:Instanciation.getArmee(faction.getValue())) {
			groupe.getItems().add(armee);
		}
		groupe.setValue(groupe.getItems().getFirst());
		
		faction.setOnAction(e->{
			groupe.getItems().clear();
			for(Armee armee:Instanciation.getArmee(faction.getValue())) {
				groupe.getItems().add(armee);
			}
			groupe.setValue(groupe.getItems().getFirst());
		});
			
		
		iv1.setFitHeight(hauteur/15);
		iv1.setPreserveRatio(true);
		iv1.setSmooth(true);
		iv1.setCache(true);
		
		iv2.setFitHeight(hauteur/15);
		iv2.setPreserveRatio(true);
		iv2.setSmooth(true);
		iv2.setCache(true);
		
		titre.setFont(Font.font(32));
		titre.setPrefHeight(50);
		titre.setAlignment(Pos.CENTER);
		
		boite.getChildren().addAll(gauche,droite);
		boite.setAlignment(Pos.CENTER);
		
		gauche.getChildren().add(entete);
		gauche.getChildren().add(gaucheCorps);
		gauche.setPrefWidth(largeur/2);
		gauche.setPrefHeight(hauteur);
		gauche.setMaxWidth(largeur/2);
		gauche.setMaxHeight(hauteur);
		gauche.setBackground(Background.fill(Color.BLUE));
		
		droite.setPrefWidth(largeur/2);
		droite.setPrefHeight(hauteur);
		droite.setMaxWidth(largeur/2);
		droite.setMaxHeight(hauteur);
		droite.setBackground(Background.fill(Color.RED));
		
		entete.getChildren().addAll(creation,faction,iv1);
		entete.setAlignment(Pos.CENTER);
		entete.setMaxHeight(hauteur/15);
		
		gaucheCorps.getChildren().add(corpTete);
		
		corpTete.getChildren().addAll(groupe,iv2);
		corpTete.setAlignment(Pos.CENTER);
		corpTete.setMaxHeight(hauteur/15);
		
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(titre);
		root.getChildren().add(boite);
		
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Création de l'armée");
		primaryStage.show();
	}
}

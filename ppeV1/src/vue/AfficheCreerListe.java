package vue;

import java.awt.Dimension;
import java.util.ArrayList;

import controlleur.BDD;
import controlleur.Instanciation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.Armee;
import modele.ArmeeListe;
import modele.Bouton;
import modele.Faction;
import modele.Unit;
import modele.User;

public class AfficheCreerListe {
	public static void afficheCreerListe(Stage primaryStage,User session) {
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight()/2;
		double largeur = tailleEcran.getWidth()/2;
		
		AfficheTopMenu menu = new AfficheTopMenu(primaryStage, session);
		
		VBox root = new VBox();
		HBox boite = new HBox();
		VBox gauche = new VBox();
		VBox droite = new VBox();
		HBox entete = new HBox();
		VBox gaucheCorps = new VBox();
		HBox corpTete = new HBox();
		VBox droiteCorps= new VBox();
		Scene scene = new Scene(root,largeur,hauteur);
		Label titre = new Label("StatHammer");
		Image logoFaction = new Image("images/wip.jpg");
		ImageView iv1 = new ImageView(logoFaction);
		ImageView iv2 = new ImageView(logoFaction);
		Button creation = new Button("Créer et sauvegarder une Armée");
		TextField nomArmee = new TextField();
		
		ScrollPane toutUnit = new ScrollPane();
		ScrollPane unitSauv = new ScrollPane();
		VBox gaucheUnit = new VBox();
		ChoiceBox<Faction> faction = new ChoiceBox<>();
		ChoiceBox<Armee> groupe = new ChoiceBox<>();
		Instanciation.conec =new BDD();
		ArmeeListe armeeListe = new ArmeeListe(new ArrayList<Unit>(),"temp","","");
		
		for(Faction fac:Instanciation.getFaction()) {
			faction.getItems().add(fac);
		}
		faction.setValue(faction.getItems().getFirst());
		
		
		
		
		
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
		
		
		/*for(Unit unit : Instanciation.getUnite(groupe.getValue())) {
			
			droiteCorps.getChildren().add(new HBox(new Label(unit.toString() ),new Bouton("+").setOnAction2(e->{
				gaucheUnit.getChildren().clear();
				Instanciation.uniteBouton(unit, armeeListe);
				for(Unit unit2:armeeListe.getUnits()) {
					gaucheUnit.getChildren().add(new Label(unit2.toString()));
				}
			})));
		}*/
		groupe.setOnAction(e->{
			droiteCorps.getChildren().clear();
			armeeListe.getUnits().clear();
			for(Unit unit : Instanciation.getUniteOfArmy(groupe.getValue())) {
				
				droiteCorps.getChildren().add(new HBox(new Label(unit.toString() ),new Bouton("+").setOnAction2(y->{
					gaucheUnit.getChildren().clear();
					armeeListe.addUnit(unit);
					
					for(Unit unit2:armeeListe.getUnits()) {
						gaucheUnit.getChildren().add(new Label(unit2.toString()));
					}
				})));
				
			}
			
			
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
		
		
		droite.getChildren().add(toutUnit);
		droite.setPrefWidth(largeur/2);
		droite.setPrefHeight(hauteur);
		droite.setMaxWidth(largeur/2);
		droite.setMaxHeight(hauteur);
		
		
		toutUnit.setFitToHeight(true);
		toutUnit.setContent(droiteCorps);
		
		entete.getChildren().addAll(creation,nomArmee,faction,iv1);
		entete.setAlignment(Pos.CENTER);
		entete.setMaxHeight(hauteur/15);
		
		creation.setOnAction(e->{
			armeeListe.setNom(nomArmee.getText());
			session.getListes().add(armeeListe);
			try {
				Instanciation.insertListe(armeeListe, session);
				Instanciation.conec.close();
				AfficheAccueil.affiche(primaryStage, session);
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			
		});
		
		
		gaucheCorps.getChildren().add(corpTete);
		gaucheCorps.getChildren().add(unitSauv);
		
		unitSauv.setContent(gaucheUnit);
		unitSauv.setFitToHeight(true);
		
		
		
		corpTete.getChildren().addAll(groupe,iv2);
		corpTete.setAlignment(Pos.CENTER);
		corpTete.setMaxHeight(hauteur/15);
		
		root.getChildren().add(menu);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(titre);
		root.getChildren().add(boite);
		
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Création de l'armée");
		primaryStage.show();
	}
}

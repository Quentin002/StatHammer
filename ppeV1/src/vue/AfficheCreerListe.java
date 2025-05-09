package vue;

import java.awt.Dimension;
import controlleur.Instanciation;
import controlleur.StockageCreerListe;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.Bouton;
import modele.User;

public class AfficheCreerListe {
	public static void afficheCreerListe(Stage primaryStage,User session) {
		
		//Definission hauteur largeur pour afficher un peu responsive
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight()/2;
		double largeur = tailleEcran.getWidth()/2;
		
		
		
		
		
		AfficheTopMenu menu = new AfficheTopMenu(primaryStage, session);
		//initialisation de toute les variables pour l'affichage
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
		ChoiceBox<String> faction = new ChoiceBox<>();
		ChoiceBox<String> groupe = new ChoiceBox<>();
		StockageCreerListe.initArmeeListe();
		
		//remplissage de la liste des factions disponibles dans une variable static
		StockageCreerListe.initFaction();
		
		//Affichage des noms des faction récuperer dans le stockage static
		for(String fac:StockageCreerListe.getNomFac()) {
			faction.getItems().add(fac);
		}
		faction.setValue(faction.getItems().getFirst());
		
		
		
		
		//remplissage de la liste des Armée disponibles dans une variable static
		StockageCreerListe.initArmee(faction.getValue());
		
		//affichage des Armée disponible dans un faction séléctionnée
		for(String armee:StockageCreerListe.getNomArmee()) {
			groupe.getItems().add(armee);
		}
		groupe.setValue(groupe.getItems().getFirst());
		
		
		//action liste déroulante pour changer de faction et les armée afficher en conséquence
		faction.setOnAction(e->{
			groupe.getItems().clear();
			
			StockageCreerListe.initArmee(faction.getValue());
			for(String armee:StockageCreerListe.getNomArmee()) {
				groupe.getItems().add(armee);
			}
			groupe.setValue(groupe.getItems().getFirst());
		});
		
		//remplissage des unitéd d'une armée séléctionnée dans une variable static
		StockageCreerListe.initUnit(groupe.getValue());
		
		//affichage de la liste des unité présente dans l'armée séléctionnée
		for(String unit : StockageCreerListe.getNomUnit()) {
			
			//action sur le bouton + permettant d'ajouter l'unité à la liste de l'armée en création
			droiteCorps.getChildren().add(new HBox(new Label(unit.toString() ),new Bouton("+").setOnAction2(e->{
			gaucheUnit.getChildren().clear();
			//verification que l'unité ne fait pas déjà parti de l'armée en cours de création
			if(!StockageCreerListe.getArmeeListe().getUnits().contains(StockageCreerListe.getUnit(unit))) {
				StockageCreerListe.getUnit(unit).setFigurine(Instanciation.getFigurine2(unit)) ;
				StockageCreerListe.getArmeeListe().addUnit(StockageCreerListe.getUnit(unit));
			}
			
			//affichage des unité de l'armée en cours de création
			for(String unit2:StockageCreerListe.getArmeeListe().getUnitNames()) {
				//action du bouton - permettant de retirer une unité de l'armée en cours de création
				gaucheUnit.getChildren().add(new HBox(new Label(unit2.toString() ),new Bouton("-").setOnAction2(z->{
					gaucheUnit.getChildren().remove(
					StockageCreerListe.getArmeeListe().getUnits().indexOf(StockageCreerListe.getUnit(unit2)));
					StockageCreerListe.getArmeeListe().removeUnit(StockageCreerListe.getUnit(unit2));
					
					
				})));
			}
				
			})));
			
		}
		
		//Meme action que plus haut à faire au moment du changement de choix d'une armée
		groupe.setOnAction(e->{
			
			StockageCreerListe.initUnit(groupe.getValue());
			droiteCorps.getChildren().clear();
			StockageCreerListe.getArmeeListe().getUnits().clear();
			for(String unit : StockageCreerListe.getNomUnit()) {
				
				droiteCorps.getChildren().add(new HBox(new Label(unit.toString() ),new Bouton("+").setOnAction2(y->{
				gaucheUnit.getChildren().clear();
				
				if(!StockageCreerListe.getArmeeListe().getUnits().contains(StockageCreerListe.getUnit(unit))) {
					StockageCreerListe.getUnit(unit).setFigurine(Instanciation.getFigurine2(unit)) ;
					StockageCreerListe.getArmeeListe().addUnit(StockageCreerListe.getUnit(unit));
				}
				for(String unit2:StockageCreerListe.getArmeeListe().getUnitNames()) {
					gaucheUnit.getChildren().add(new HBox(new Label(unit2.toString() ),new Bouton("-").setOnAction2(z->{
						gaucheUnit.getChildren().remove(
						StockageCreerListe.getArmeeListe().getUnits().indexOf(StockageCreerListe.getUnit(unit2)));
						StockageCreerListe.getArmeeListe().removeUnit(StockageCreerListe.getUnit(unit2));
												
					})));
				}
				})));
				
			}
		});
			
		//assemblage des éléments visuels 
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
		
		//Bouton de validation de création d'une armée pour sauvegarder l'armée en base de donnée
		creation.setOnAction(e->{
			//verification que l'armée soit nommé et NON VIDE
			if(nomArmee.getText().trim().equals("") || StockageCreerListe.getArmeeListe().getUnits().size()==0) {
				//affichage d'une boite d'erreur si nom vide ou armée sans unité
				Stage secondaryStage = new Stage();
				HBox boitesecondaire = new HBox();
				Scene secondScene = new Scene(boitesecondaire,200,100);
				Label error = new Label("ERREUR Armee vide ET/OU sans nom");
				boitesecondaire.getChildren().add(error);
				boitesecondaire.setAlignment(Pos.CENTER);
				secondaryStage.setScene(secondScene);
				secondaryStage.setTitle("Error");
				secondaryStage.show();
			}else {
				//sauvegarde de l'armée dans la variable de session ainsi que dans la BDD
				StockageCreerListe.getArmeeListe().setNom(nomArmee.getText());
				session.getListes().add(StockageCreerListe.getArmeeListe());
				try {
					
					Instanciation.insertListe(StockageCreerListe.getArmeeListe(), session);
					
					AfficheAccueil.affiche(primaryStage, session);
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
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
	

package vue.simulation;


import vue.AfficheTopMenu;

import java.util.ArrayList;

import application.Battle;
import controlleur.ControlleurSimu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.ArmeeListe;
import modele.User;

public class AfficheSimulation
{
	private static Battle battle_data = new Battle(); // mémoire des actions de l'utilisateurs, liste, unités, PV, armes, aptitudes
	private static SimAptAndWeaponsVBox weapons_aptitudes_menu = new SimAptAndWeaponsVBox();
	public static boolean inversion = false; // pour le bouton inversion
	
	public static Battle getBattleData() {
		return battle_data;
	}
	
	public static void refreshWeaponAndAptitude(SimAptAndWeaponsVBox wap)
	{
		weapons_aptitudes_menu.getChildren().clear();
		weapons_aptitudes_menu.getChildren().addAll(wap.getChildren()); // copie profonde
		//weapons_aptitudes_menu.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 2; -fx-padding: 3px;");
		weapons_aptitudes_menu.setStyle("-fx-background-color: rgb(210, 210, 210); -fx-padding: 4px; -fx-border-color: black; -fx-border-radius: 4;");
	}
	
	public static SimAptAndWeaponsVBox getWeaponsAtitudesMenu() {
		return weapons_aptitudes_menu;
	}
	public static void cleanWeaponsAtitudesMenu() {
		weapons_aptitudes_menu = new SimAptAndWeaponsVBox();
	}
	public static void cleanBattle() {
		battle_data = new Battle();
	}
	
	public static void affiche(Stage primaryStage, User session)
	{
		// listes d'armées
		ArrayList<ArmeeListe> lists_of_lists = session.getListes(); // listes d'objets ArmeeListe
		String[] list_names = session.getListNames(); // tableau des noms des listes
		
		VBox root_box = new VBox();
		AfficheTopMenu menu = new AfficheTopMenu(primaryStage, session); // menu du haut de l'écran
		HBox main = new HBox(); // partie principale de la fenêtre
		
		
        /* -- colonne de gauche -- */
		VBox column1_box = new VBox();
		column1_box.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
		
		ScrollPane column1_scrollpane = new ScrollPane(); // conteneur avec barres de défilement
        column1_scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // pas de barre horizontale
        
        VBox column1 = new VBox();
        column1.prefWidthProperty().bind(column1_scrollpane.widthProperty());
        
        
        // menu choix armée
        HBox col1_first_row = new HBox(); // menu déroulant + logo faction
        ChoiceBox<String> list1_drop_down = new ChoiceBox<>();
        list1_drop_down.setStyle("-fx-font-size: 15;");
        list1_drop_down.prefWidthProperty().bind(column1_scrollpane.widthProperty());
        for(int i = 0; i < list_names.length; i++)
        {
        	list1_drop_down.getItems().add(list_names[i]);
        }
        col1_first_row.getChildren().add(list1_drop_down);
        column1.getChildren().add(col1_first_row);
        
        // unités colonne 1 (par défaut)
        SimUnitsVBox units_list1 = new SimUnitsVBox(1);
        column1.getChildren().add(units_list1);
        column1_scrollpane.setContent(column1);
        
        VBox.setMargin(weapons_aptitudes_menu, new Insets(5,2,2,2));
        column1_box.getChildren().addAll(column1_scrollpane, weapons_aptitudes_menu);
        
        
        /* -- colonne centrale -- */
        VBox column2 = new VBox();
        column2.prefWidthProperty().bind(main.widthProperty().multiply(0.4));
        column2.setAlignment(Pos.TOP_CENTER);
        
        	// boutons inverser et simuler   
		Button btn_simulate = new Button("Action !!");
		btn_simulate.setStyle("-fx-font-size: 15;");
		
		Image icons_inversion = new Image("/images/inversion_icons.png");
		ImageView icons_inversion_box = new ImageView(icons_inversion);
		Button btn_reverse_armies = new Button();
		btn_reverse_armies.setGraphic(icons_inversion_box);
        
        	// grande image
        Image image_col2 = new Image("/images/wip.jpg");
		ImageView image_box = new ImageView();
		image_box.setPreserveRatio(true);
		image_box.fitWidthProperty().bind(column2.widthProperty());
		image_box.setImage(image_col2);
		
			// contrairement aux VBox, ce conteneur ne s'adapte pas à la taille des ses enfants, c'est l'image qui s'adapte
		Pane big_image_pane = new Pane();
		big_image_pane.getChildren().add(image_box);
		
			// assemblage
		column2.getChildren().addAll(btn_simulate, big_image_pane, btn_reverse_armies);
		
		
        /* -- colonne de droite -- */
		ScrollPane column3_box = new ScrollPane(); // conteneur avec barres de défilement
        column3_box.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
        column3_box.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // pas de barre horizontale
        
        VBox column3 = new VBox();
        column3.prefWidthProperty().bind(column3_box.widthProperty());
        
        HBox col3_first_row = new HBox(); // menu déroulant + logo faction
        ChoiceBox<String> list2_drop_down = new ChoiceBox<>();
        list2_drop_down.setStyle("-fx-font-size: 15;");
        list2_drop_down.prefWidthProperty().bind(column1_scrollpane.widthProperty());
        for(int i = 0; i < list_names.length; i++)
        {
        	list2_drop_down.getItems().add(list_names[i]);
        }
        col3_first_row.getChildren().add(list2_drop_down);
        column3.getChildren().add(col3_first_row);
        
        // unités colonne 3
        SimUnitsVBox units_list2 = new SimUnitsVBox(2);
        column3.getChildren().add(units_list2);
        column3_box.setContent(column3);
        
        
        /* -- assemblage -- */
		main.getChildren().addAll(column1_box, column2, column3_box);
		root_box.getChildren().addAll(menu, main);
		
		
		/* ACTION !! */
		btn_simulate.setOnAction(e -> {
			if(battle_data.getSelectedList(1) != null && battle_data.getSelectedList(2) != null
				&& battle_data.getSelectedUnitIndex(1) >= 0 && battle_data.getSelectedUnitIndex(2)  >= 0)
			{
				ControlleurSimu.afficheSimu(big_image_pane, battle_data.getSelectedUnit(1), battle_data.getSelectedUnit(2));
			}
			else {
				System.out.println("conditions non remplies pour faire une simulation");
			}
		});
		
		
		Scene scene = new Scene(root_box,800,600);
		primaryStage.setTitle("Simulation");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		// choix des listes d'armées
		ArrayList<SimUnitsVBox> unit_lists = new ArrayList<SimUnitsVBox>();
		unit_lists.add(units_list1);
		unit_lists.add(units_list2);
        ArrayList<ChoiceBox<String>> lists_drop_down = new ArrayList<ChoiceBox<String>>();
        lists_drop_down.add(list1_drop_down);
        lists_drop_down.add(list2_drop_down);;
        ArrayList<HBox> first_rows = new ArrayList<HBox>();
        first_rows.add(col1_first_row);
        first_rows.add(col3_first_row);
        for(int i = 0; i < 2; i++)
        {
        	final int j = i;
        	lists_drop_down.get(i).getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)  -> {
        		if(!inversion) {
        			ControlleurSimu.selectAList(j + 1, lists_of_lists, newValue);
        		}
        		ControlleurSimu.PullDownUnits(j + 1, first_rows.get(j), unit_lists.get(j));
            });
        }
        
        // inversion de l'attaquant et du défenseur
 		btn_reverse_armies.setOnAction(e -> {
 			if(battle_data.getSelectedList(1) != null && battle_data.getSelectedList(2) != null) {
 				ControlleurSimu.reverseArmies(lists_drop_down);
 			}
 			else {
 				System.out.println("conditions non remplies pour inverser listes");
 			}
 		});
	}
}

package vue.simulation;

import vue.afficheTopMenu;
import java.util.ArrayList;
import java.util.HashMap;

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
import modele.Arme;
import modele.ArmeMelee;
import modele.Figurine;
import modele.Unit;
import modele.User;

public class AfficheSimulation
//public class Main extends Application
{
	private static SimAptAndWeaponsVBox weapons_aptitudes_menu = new SimAptAndWeaponsVBox();
	
	public static void setColumn1Bottom(SimAptAndWeaponsVBox wap)
	{
		weapons_aptitudes_menu.getChildren().clear();
		//weapons_aptitudes_menu = wap; // marche pas
		weapons_aptitudes_menu.getChildren().addAll(wap.getChildren());
		weapons_aptitudes_menu.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 2; -fx-padding: 3px;");
	}
	
	// run config: --module-path "C:\Users\greta\CODE\eclipse-java\javafx-sdk-21.0.6\lib" --add-modules javafx.controls
//	public static void main(String[] args){
//		launch(args);
//	}
	
//	@Override
//	public void start(Stage primaryStage)
	public static void affiche(Stage primaryStage,User session)
	//public static void affiche(Stage primaryStage, ArrayList<ArmeeListe> liste)
	{
		// listes d'armées
		// y mettre en réalité les noms des listes d'armée
//		for(ArmeeListe one_list : liste)
//			String[] attacker_lists =
//			String[] defenser_lists =
//		{}
		String[] attacker_lists = {"Liste 1", "Liste 2", "Liste 3"};
		String[] defender_lists = {"Liste 1", "Liste 2", "Liste 3"};
		HashMap<String, String[]> units_names =  new HashMap<String, String[]>();
		units_names.put("Liste 1", new String[]{"Unité 1", "Unité 2", "Unité 3", "Unité 4", "Unité 5"});
		units_names.put("Liste 2", new String[]{"Unité 6", "Unité 7", "Unité 8", "Unité 9"});
		units_names.put("Liste 3", new String[]{"Unité 10", "Unité 11"});

		VBox root_box = new VBox();
		afficheTopMenu menu = new afficheTopMenu(primaryStage,session); // menu du haut de l'écran
		HBox main = new HBox(); // partie principale de la fenêtre
		
		
        /* -- colonne de gauche -- */
		VBox column1_box = new VBox();
		column1_box.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
		
		ScrollPane column1_scrollpane = new ScrollPane(); // conteneur avec barres de défilement
        column1_scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // pas de barre horizontale
        
        VBox column1 = new VBox();
        column1.prefWidthProperty().bind(column1_scrollpane.widthProperty());
        
        
        // menu choix armée
        HBox col1_first_row = new HBox();
        ChoiceBox<String> list1_drop_down = new ChoiceBox<>();
        list1_drop_down.setStyle("-fx-font-size: 15;");
        list1_drop_down.prefWidthProperty().bind(column1_scrollpane.widthProperty());
        for(int i = 0; i < attacker_lists.length; i++)
        {
        	list1_drop_down.getItems().add(attacker_lists[i]);
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
        
        	// boutons inverser et simuler   
		Image icons_inversion = new Image("/images/inversion_icons.png");
		ImageView icons_inversion_box = new ImageView(icons_inversion);
		Button reverse_armies = new Button();
		reverse_armies.setGraphic(icons_inversion_box);
		Button btn_simulate = new Button("Action !!");
		btn_simulate.setStyle("-fx-font-size: 15;");
		
		HBox column2_top = new HBox();
        column2_top.setAlignment(Pos.CENTER);
		column2_top.getChildren().addAll(reverse_armies, btn_simulate);
        
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
		column2.getChildren().addAll(column2_top, big_image_pane);
		
		
        /* -- colonne de droite -- */
		ScrollPane column3_box = new ScrollPane(); // conteneur avec barres de défilement
        column3_box.prefWidthProperty().bind(main.widthProperty().multiply(0.3));
        column3_box.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // pas de barre horizontale
        
        VBox column3 = new VBox();
        column3.prefWidthProperty().bind(column3_box.widthProperty());
        
        HBox col3_first_row = new HBox();
        ChoiceBox<String> list2_drop_down = new ChoiceBox<>();
        list2_drop_down.setStyle("-fx-font-size: 15;");
        list2_drop_down.prefWidthProperty().bind(column1_scrollpane.widthProperty());
        for(int i = 0; i < defender_lists.length; i++)
        {
        	list2_drop_down.getItems().add(defender_lists[i]);
        }
        col3_first_row.getChildren().add(list2_drop_down);
        column3.getChildren().add(col3_first_row);
        
        // unités colonne 3
        SimUnitsVBox units_list2 = new SimUnitsVBox(3);
        column3.getChildren().add(units_list2);
        column3_box.setContent(column3);
        
        
        /* -- assemblage -- */
		main.getChildren().addAll(column1_box, column2, column3_box);
		root_box.getChildren().addAll(menu, main);
		
		
		btn_simulate.setOnAction(e -> {
			ArmeMelee w1 = new ArmeMelee("t1",3,4,1,2,1);
			ArmeMelee w2 = new ArmeMelee("t2",8,2,3,2,3);
			ArrayList<Arme> l1= new ArrayList<>();
			l1.add(w1);
			l1.add(w2);
			Figurine fi1 = new Figurine("test1",6,2,3,l1);
			
			Figurine fi3 = new Figurine("test15",4,4,2,l1);
			Figurine fi4 = new Figurine("test14",6,2,3,l1);
			Figurine fi5 = new Figurine("test13",6,2,3,l1);
			Figurine fi6 = new Figurine("test12",6,2,3,l1);
			Figurine fi2 = new Figurine("test9",6,2,2,l1);
			Figurine fi7 = new Figurine("test76",4,4,2,l1);
			ArrayList<Figurine> fu1 = new ArrayList<Figurine>();
			fu1.add(fi1);
			fu1.add(fi2);
			fu1.add(fi3);
			fu1.add(fi4);
			fu1.add(fi5);
			fu1.add(fi6);
			ArrayList<Figurine> fu2 = new ArrayList<Figurine>();
			fu2.add(fi3);
			fu2.add(fi7);
			Unit u1 = new Unit("unite1",fu1);
			Unit u2 = new Unit("unite2",fu2);
			//Calcul.bataille(u1, u2,big_image_pane);
			ControlleurSimu.afficheSimu(big_image_pane,u1,u2);
		});
		
		
		Scene scene = new Scene(root_box,800,600);
		primaryStage.setTitle("Simulation");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		// choix des listes d'armées
		ArrayList<SimUnitsVBox> lists = new ArrayList<SimUnitsVBox>();
		lists.add(units_list1);
		lists.add(units_list2);
        ArrayList<ChoiceBox<String>> lists_drop_down = new ArrayList<ChoiceBox<String>>();
        lists_drop_down.add(list1_drop_down);
        lists_drop_down.add(list2_drop_down);;
        ArrayList<HBox> first_rows = new ArrayList<HBox>();
        first_rows.add(col1_first_row);
        first_rows.add(col3_first_row);
        for(int i = 0; i < 2; i++)
        {
        	final int j = i;
        	lists_drop_down.get(i).setOnAction(e -> {
        		
        		// Passer par un contrôleur pour obtenir les logos de factions et les unités!!
        		//ControlleurSimu.changeList(lists_drop_down.get(j).getValue());
        		
        		// logo de faction
        		String file_name = "android-fill.png"; // à récupérer par le contrôleur
        		Image logo_faction = new Image("/images/" + file_name);
        		ImageView logo_box = new ImageView();
        		logo_box.setPreserveRatio(true);
        		logo_box.fitHeightProperty().bind(lists_drop_down.get(j).heightProperty());
        		logo_box.setImage(logo_faction);
        		if(first_rows.get(j).getChildren().size() == 2){
        			first_rows.get(j).getChildren().remove(1);
        		}
        		first_rows.get(j).getChildren().add(logo_box);
        		
        		// effacer la zone armes et aptitudes
        		weapons_aptitudes_menu.setStyle("-fx-border-width: 0;");
        		weapons_aptitudes_menu.getChildren().clear();
        		// effacer et recréer les unités
        		lists.get(j).getChildren().clear();
        		lists.get(j).setList(units_names.get(lists_drop_down.get(j).getValue()));
        		// remettre la capture des boutons pour dérouler les unités
        		dropdownUnitButtonsAction(lists);
            });
        }
        
        dropdownUnitButtonsAction(lists); // 1ère fois
	}
	
	private static void dropdownUnitButtonsAction(ArrayList<SimUnitsVBox> lists)
	{
		// boutons qui déplient les unités
		// on met ça ici et non dans SimUnitsVBox parce qu'on gère en même temps la SimAptAndWeaponsVBox
        // cette évènement devait être géré dans SimUnitsVBox, mais on a besoin de le faire ici
        for(int c = 0; c < 2; c++) // les c 1 et 2 correspondent aux colonnes 1 et 3
        {
        	ArrayList<Button> dropdown_unit_buttons = lists.get(c).getButtons();
            ArrayList<SimFigurinesVBox> fig_boxes = lists.get(c).getFigBoxes();
            
	 		for(int i = 0; i < dropdown_unit_buttons.size(); i++)
	 		{
	 			//buttons_units_list1.get(i).setStyle("-fx-border-width: 0; -fx-border-color: yellow; -fx-border-radius: 2;"); // style des bordures
	 			final int col = c;
	 			final int j = i; // merci chatgpt pour le trick!
	 			// impossible d'écrire unit_box.get(i), i ne peut entrer dans la fonction lambda parce qu'il est susceptible de changer à l'extérieur de celle-ci
	 			
	 			/* -- BOUTONS des unités --*/
	 			dropdown_unit_buttons.get(i).setOnAction(e ->
	 			{
	 				// dérouler ou replier les figurines
	 				fig_boxes.get(j).changeState();
	 				
	 				// retirer bordure unité désélectionnée
	 				if(lists.get(col).getSelectedUnit() > 0)
	 				{
	 					dropdown_unit_buttons.get(lists.get(col).getSelectedUnit() - 1).setStyle("-fx-border-width: 0;");
	 				}
	 				
	 				if(fig_boxes.get(j).isOpen())
	 				{
	 					// afficher figurines
	 					fig_boxes.get(j).setFigurines();
	 					if(lists.get(col).getSelectedUnit() != j + 1)
	 					{
	 						lists.get(col).setSelectedUnit(j + 1);
	 						if(col == 0)
	 						{
	 							weapons_aptitudes_menu.getChildren().clear();
	 						}
	 					}
	 					// bordure unité sélectionnée
	 					dropdown_unit_buttons.get(j).setStyle("-fx-border-width: 2; -fx-border-color: yellow; -fx-border-radius: 2;");
	 				}
	 				else
	 				{
	 					// cacher figurine
	 					fig_boxes.get(j).getChildren().clear(); // index est constant dans sa portée, alors que i change
	 					if(col == 0)
	 					{
	 						weapons_aptitudes_menu.getChildren().clear();
	 					}
	 					lists.get(col).setSelectedUnit(0); // valeur "impossible"
	 					
	 					// retirer bordure unité désélectionnée
	 					dropdown_unit_buttons.get(j).setStyle("-fx-border-width: 0;");
	 				}
	 			});
	 		}
        }
	}
}

package controlleur;

import java.util.ArrayList;
import java.util.HashMap;

import application.Battle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Aptitude;
import modele.Arme;
import modele.ArmeeListe;
import modele.Calcul;
import modele.Figurine;
import modele.Unit;
import vue.simulation.AfficheSimulation;
import vue.simulation.SimAptAndWeaponsVBox;
import vue.simulation.SimFigurinesVBox;
import vue.simulation.SimHistogramme;
import vue.simulation.SimUnitsVBox;

public class ControlleurSimu
{
	private static Battle battle_data;
	
	// déroulement des unités d'une liste (appui sur lists_drop_down.get(i) dans AfficheSimulation)
	public static void selectAList(int num_list, ArrayList<ArmeeListe> lists, String choice) {
		battle_data = AfficheSimulation.getBattleData();
		
		// choix de la liste
		for(ArmeeListe one_list : lists)
		{
			if(one_list.getName().equals(choice)) {
				ArmeeListe list = new ArmeeListe(one_list.getName(), one_list.getDescription(), one_list.getData());
				battle_data.setSelectedList(num_list, list);
				list.setName(one_list.getName());
				list.setDescription(one_list.getDescription());
				list.setData(one_list.getData());
				
				break; // choix unique
			}
		}
		
		// obtenir les unités, l'armée, les figurines, armes et aptitudes
		Instanciation.conec = new BDD();
		Instanciation.getUnitsOfAList(battle_data.getSelectedList(num_list));
		Instanciation.conec.close();
		if(battle_data.getSelectedList(num_list).getUnits().size() > 0) {
			battle_data.setArmy(num_list, battle_data.getSelectedList(num_list).getUnits().get(0).getArmee());
		}
		else {
			System.out.println("liste vide ma gueule");
		}
	}
	
	// déroulement des figurines d'une unité (appui sur buttons.get(i) dans SimUnitsVBox)
	public static void PullDownUnits(int num_list, HBox dropdown_menu_box, SimUnitsVBox list_box) {
		ArmeeListe list = battle_data.getSelectedList(num_list);
		
		// noms des unités
		String[] unit_names = new String[list.getUnits().size()];
		for(int i = 0; i < list.getUnits().size(); i++) {
			unit_names[i] = list.getUnits().get(i).getName();
		}
		
		// supprimer l'ancien logo
		if(dropdown_menu_box.getChildren().size() == 2){
			dropdown_menu_box.getChildren().remove(1);
		}
		
		// logo de faction
		Image logo_faction = null;
		if(battle_data.getArmy(num_list) != null) // possibilité d'une liste vide
		{
			String file_name = battle_data.getArmy(num_list).getLogo();
			logo_faction = new Image("/images/armees/" + file_name);
		}
		ImageView logo_box = new ImageView();
		
		logo_box.setPreserveRatio(true);
		logo_box.fitHeightProperty().bind(dropdown_menu_box.heightProperty());
		logo_box.setImage(logo_faction);
		dropdown_menu_box.getChildren().add(logo_box);
		
		// effacer la zone armes et aptitudes
		AfficheSimulation.getWeaponsAtitudesMenu().setStyle("-fx-border-width: 0;");
		AfficheSimulation.getWeaponsAtitudesMenu().getChildren().clear();
		
		// effacer et recréer les unités
		list_box.getButtons().clear();
		list_box.getFigBoxes().clear();
		list_box.getChildren().clear();
		list_box.setList(num_list);
	}
	
	// déroulement des figurines d'une unité (appui sur buttons.get(i) dans SimUnitsVBox)
	public static void selectAnUnit(SimUnitsVBox lists, int col, int j)
	{
		ArrayList<Button> buttons = lists.getButtons();
		SimFigurinesVBox old_fig_boxes = null;
        SimFigurinesVBox new_fig_boxes = lists.getFigBoxes().get(j);
        
        // il y a déjà une unité sélectionnée
        if(battle_data.getSelectedUnitIndex(col) > -1)
        {
			buttons.get(battle_data.getSelectedUnitIndex(col)).setStyle("-fx-border-width: 0;");// retirer bordure unité sélectionnée
        	AfficheSimulation.getWeaponsAtitudesMenu().getChildren().clear(); // supprimer fenêtre combat si existe
        	
        	// bouton même unité => ferme
        	if(battle_data.getSelectedUnitIndex(col) == j)
            {
        		new_fig_boxes.getChildren().clear();
        		battle_data.setSelectedUnit(col, -1);
            }
        	// bouton autre unité
        	else
        	{
        		old_fig_boxes = lists.getFigBoxes().get(battle_data.getSelectedUnitIndex(col));
	        	if(old_fig_boxes != null) {
					old_fig_boxes.getChildren().clear(); // supprimer ancienne zone de figurines
				}
	        	battle_data.setSelectedUnit(col, j);
	        	buttons.get(j).setStyle("-fx-border-width: 2; -fx-border-color: yellow; -fx-border-radius: 2;"); // bordure unité sélectionnée
	        	new_fig_boxes.setFigurines(); // on ouvre
        	}
        }
        // pas d'unité sélectionnée => on ouvre
        else
        {
        	battle_data.setSelectedUnit(col, j);
        	buttons.get(j).setStyle("-fx-border-width: 2; -fx-border-color: yellow; -fx-border-radius: 2;"); // bordure unité sélectionnée
        	new_fig_boxes.setFigurines();
        }
	}
	
	// slider des PV des figurines
	public static void hpUpdate(int hp, Figurine fig, ImageView one_image_box) {
		int old_hp = fig.getHP();
		fig.setHP(hp);
		
		Image one_image;
		if(old_hp == 0 && fig.getHP() > 0) {
			one_image = new Image("/images/android-fill.png");
			one_image_box.setImage(one_image);
		}
		else if(old_hp > 0 && fig.getHP() == 0){ // utile?
			one_image = new Image("/images/android-line.png");
			one_image_box.setImage(one_image);
		}
	}
	
	// afficher la zone des armes et aptitudes
	public static void makeWeaponsAndAptitudeZone(ArrayList<Figurine> fig_group, SimAptAndWeaponsVBox weapons_aptitudes_menu) {
		ArrayList<Arme> weapon_list = fig_group.get(0).getArmes();
		ArrayList<Aptitude> aptitude_list = fig_group.get(0).getAptitudes();
		
		weapons_aptitudes_menu.setArmes(weapon_list);
		weapons_aptitudes_menu.setAptitudes(aptitude_list);
		weapons_aptitudes_menu.setAptAndWeapons(fig_group); // contenu de la vue
		AfficheSimulation.refreshWeaponAndAptitude(weapons_aptitudes_menu); // insersion dans la vue principale
	}
	
	public static void selectAWeapon(String weapon_name, ArrayList<Figurine> fig_group) {
		Arme weapon = fig_group.get(0).getWeaponByName(weapon_name); // recherche dans les armes de la 1ère figurine
		// on équipe le groupe
		for(Figurine fig : fig_group) {
			fig.setWeapon(weapon);
		}
	}
	
	public static void weaponNumberChoice(int value, String group_name) {
		// on considère que ceux qui n'attaquent pas sont morts
		ArrayList<Figurine> fig_group = AfficheSimulation.getBattleData().getSelectedUnit(1).getIdenticalFigsGroups().get(group_name);
		for(int i = 0; i < value; i++) {
			fig_group.get(i).setHP(fig_group.get(i).getHPMax());
		}
		for(int i = value; i < fig_group.size(); i++) {
			fig_group.get(i).setHP(0);
		}
		
		System.out.println("weaponNumberChoice => trouver un moyen de modifier instantanément les images");
	}
	
	// méthode pas encore testée
	public static void checkOrUncheckAnAptitude(boolean isSelected, String aptitude_name, ArrayList<Figurine> fig_group) {
		for(Aptitude apti : fig_group.get(0).getAptitudes()) // recherche dans les aptitudes de la 1ère figurine
		{
			if(apti.getName() == aptitude_name) {
				for(Figurine fig : fig_group)
				{
					HashMap<String, Aptitude> selectedAptitudes = fig.getSelectedAptitudes();
					if(isSelected) {
						selectedAptitudes.put(aptitude_name, apti);
						System.out.println("debug: " + fig.getSelectedAptitudes().get(aptitude_name) + " is selected");
					}
					else {
						if(selectedAptitudes.containsKey(aptitude_name)) {
							selectedAptitudes.remove(aptitude_name);
							System.out.println("taille de selectedAptitudes = " + fig.getSelectedAptitudes().size());
						}
					}
				}
				break; // on ne gère pas le cas où deux aptitudes ont le même nom
			}
		}
	}
	
	// histogramme
	public static void afficheSimu(Pane p,Unit u1,Unit u2) {
		p.getChildren().clear();
		Calcul c =Calcul.bataille(u1, u2);
		
		SimHistogramme.setHist(c.getTabdegat1(), c.getTabmort1(), c.getDegat_moyen1(), c.getMort_moyen1(),p);
	}
}

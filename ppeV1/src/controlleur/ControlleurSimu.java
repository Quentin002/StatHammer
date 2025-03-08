package controlleur;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.ArmeeListe;
import modele.Calcul;
import modele.Unit;
import vue.simulation.AfficheSimulation;
import vue.simulation.SimFigurinesVBox;
import vue.simulation.SimHistogramme;
import vue.simulation.SimUnitsVBox;

public class ControlleurSimu
{
	// déroulement des unités d'une liste (appui sur lists_drop_down.get(i) dans AfficheSimulation)
	public static void selectAList(int num_list, ArrayList<ArmeeListe> lists, String choice) {
		// choix de la liste
		for(ArmeeListe one_list : lists)
		{
			if(one_list.getName().equals(choice)) {
				ArmeeListe list = new ArmeeListe(one_list.getName(), one_list.getDescription(), one_list.getData());
				AfficheSimulation.setSelectedList(num_list, list);
				list.setName(one_list.getName());
				list.setDescription(one_list.getDescription());
				list.setData(one_list.getData());
				
				break; // choix unique
			}
		}
		
		// obtenir les unités, l'armée, les figurines, armes et aptitudes
		Instanciation.conec = new BDD();
		Instanciation.getUnitsOfAList(AfficheSimulation.getSelectedList(num_list));
		Instanciation.conec.close();
		AfficheSimulation.setArmy(num_list,
		AfficheSimulation.getSelectedList(num_list).getUnits().get(0).getArmee());
	}
	
	// appui sur lists_drop_down.get(i) dans AfficheSimulation
	public static void PullDownUnits(int num_list, HBox dropdown_menu_box, SimUnitsVBox list_box) {
		ArmeeListe list = AfficheSimulation.getSelectedList(num_list);
		
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
		if(AfficheSimulation.getArmy(num_list) != null) // possibilité d'une liste vide
		{
			String file_name = AfficheSimulation.getArmy(num_list).getLogo();
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
		list_box.getChildren().clear();
		list_box.setList(num_list);
	}
	
	// déroulement des figurines d'une unité (appui sur dropdown_unit_buttons.get(i) dans AfficheSimulation)
	public static void selectAnUnit(ArrayList<SimUnitsVBox> lists, int col, int j)
	{
		ArrayList<Button> dropdown_unit_buttons = lists.get(col).getButtons();
        ArrayList<SimFigurinesVBox> fig_boxes = lists.get(col).getFigBoxes();
        
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
					AfficheSimulation.getWeaponsAtitudesMenu().getChildren().clear();
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
				AfficheSimulation.getWeaponsAtitudesMenu().getChildren().clear();
			}
			lists.get(col).setSelectedUnit(0); // valeur "impossible"
			
			// retirer bordure unité désélectionnée
			dropdown_unit_buttons.get(j).setStyle("-fx-border-width: 0;");
		}
	}
	
	// histogramme
	public static void afficheSimu(Pane p,Unit u1,Unit u2) {
		p.getChildren().clear();
		Calcul c =Calcul.bataille(u1, u2);
		
		SimHistogramme.setHist(c.getTabdegat1(), c.getTabmort1(), c.getDegat_moyen1(), c.getMort_moyen1(),p);
	}
}

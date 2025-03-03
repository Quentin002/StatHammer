package controlleur;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.ArmeeListe;
import modele.Calcul;
import modele.Unit;
import vue.simulation.AfficheSimulation;
import vue.simulation.SimHistogramme;
import vue.simulation.SimUnitsVBox;

public class ControlleurSimu {
	static BDD db = new BDD();
//	private Pane p;
//	private Unit u1;
//	private Unit u2;
	
	// inutile
//	public ControlleurSimu(Pane p, Unit u1, Unit u2) {
//		super();
//		this.p = p;
//		this.u1 = u1;
//		this.u2 = u2;
//	}
	public static void selectAList(ArrayList<ArmeeListe> lists, String choice) {
		// choix de la liste
		ArmeeListe list = null;
		for(ArmeeListe one_list : lists)
		{
			if(one_list.getName().equals(choice)) {
				AfficheSimulation.setSelectedList(one_list);
				list = one_list;
			}
		}
		
		// obtenir les unités
		db.getUnits(list);
		
		// récupérer la faction de la 1ère unité
		if(list.getUnits().size() > 0){
			AfficheSimulation.setArmy(db.getArmy(list.getUnits().get(0)));
		}
		
		// rassembler les deux requêtes???
	}
	
	public static void PullDownUnits(HBox dropdown_menu_box, SimUnitsVBox list_box) {
		ArmeeListe list = AfficheSimulation.getSelectedList();
		
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
		if(AfficheSimulation.getArmy() != null) // possibilité d'une liste vide
		{
			String file_name = AfficheSimulation.getArmy().getLogo();
			logo_faction = new Image("/images/" + file_name);
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
		list_box.setList(unit_names);
	}
	
	// histogramme
	public static void afficheSimu(Pane p,Unit u1,Unit u2) {
		p.getChildren().clear();
		Calcul c =Calcul.bataille(u1, u2);
		
		SimHistogramme.setHist(c.getTabdegat1(), c.getTabmort1(), c.getDegat_moyen1(), c.getMort_moyen1(),p);
	}
}


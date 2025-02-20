package vue.simulation;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SimUnitsVBox extends VBox
{
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<SimFigurinesHBox> unit_box = new ArrayList<SimFigurinesHBox>();
	//private int selected_unit;
	
	//public SimUnitsVBox(){}
	
//	public int getSelectedUnit(){
//		return selected_unit;
//	}
	
	// getters
	public ArrayList<Button> getButtons(){
		return buttons;
	}
	public ArrayList<SimFigurinesHBox> getUnitBox(){
		return unit_box;
	}

	// générer les listes d'unités à afficher 
	public void setList(String liste_name)
	{
		String[] units_names = {"Unité 1", "Unité 2", "Unité 3", "Unité 4", "Unité 5"};
		
		// création tableaux de boutons et VBox et ajout au noeud parent (SimUnitsVBox)
		for(int i = 0; i < units_names.length; i++)
		{
			// numéro de l'unité dans un carré noir
			
			
			// boutons pour déplier
			Button tmp_button = new Button(units_names[i]);
			tmp_button.setMaxWidth(Double.MAX_VALUE);
			buttons.add(tmp_button);
			this.getChildren().add(buttons.get(i));
			
			// zones de figurines vides
			SimFigurinesHBox one_fig_box = new SimFigurinesHBox();
			//tmp_vbox.setMaxWidth(Double.MAX_VALUE); // à remettre après test de remplissage
			unit_box.add(one_fig_box);
			this.getChildren().add(unit_box.get(i));
		}
		
//		// appuis sur les boutons
//		for(int i = 0; i < buttons.size(); i++)
//		{
//			final int j = i; // merci chatgpt pour le trick!
//			// impossible d'écrire unit_box.get(i), i ne peut entrer dans la fonction lambda parce qu'il est susceptible de changer à l'extérieur de celle-ci
//			
//			buttons.get(i).setOnAction(e ->
//			{
//				unit_box.get(j).changeState();
//				if(unit_box.get(j).isOpen())
//				{
//					// afficher figurines
//					unit_box.get(j).setFigurines();
//					//selected_unit = j + 1;
//				}
//				else
//				{
//					// cacher figurine
//					unit_box.get(j).getChildren().clear(); // index est constant dans sa portée, alors que i change
//				}
//			});
//		}
		
	}
}

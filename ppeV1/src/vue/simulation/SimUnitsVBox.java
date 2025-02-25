package vue.simulation;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SimUnitsVBox extends VBox
{
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<SimFigurinesVBox> fig_boxes = new ArrayList<SimFigurinesVBox>();
	private int selected_unit = 0;
	private int column;
	
	public SimUnitsVBox(int col){
		column = col;
		this.setStyle("-fx-padding: 2px;");
	}
	
	public int getSelectedUnit(){
		return selected_unit;
	}
	public void setSelectedUnit(int unit) {
		selected_unit = unit;
	}
	
	// getters
	public ArrayList<Button> getButtons(){
		System.out.println(buttons);
		return buttons;
	}
	public ArrayList<SimFigurinesVBox> getFigBoxes(){
		return fig_boxes;
	}

	// générer les listes d'unités à afficher 
	public void setList(String[] units_names)
	{
		String[][] figs_names = {{"Héros"},
				{"Figurine 1", "Figurine 2", "Figurine 3", "Figurine 4", "Figurine 5", "Figurine 6"},
				{"Figurine 6", "Figurine 7", "Figurine 8"}};
		
		// cette classe est une VBox qui contient une liste d'unités
		// chaque unité est une VBox qui contient un bouton et une SimFigurineVBox
		for(int i = 0; i < units_names.length; i++)
		{
			//System.out.println(units_names[i]);
			VBox one_unit = new VBox();
			
			// boutons pour déplier
			Button tmp_button = new Button(units_names[i]);
			tmp_button.setMaxWidth(Double.MAX_VALUE);
			one_unit.getChildren().add(tmp_button);
			buttons.add(tmp_button);
			
			// zones de figurines
			SimFigurinesVBox one_fig_box = new SimFigurinesVBox(column, figs_names);
			one_unit.getChildren().add(one_fig_box);
			fig_boxes.add(one_fig_box);
			
			this.getChildren().add(one_unit);
		}
	}
}

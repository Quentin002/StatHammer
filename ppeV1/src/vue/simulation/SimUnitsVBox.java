package vue.simulation;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import modele.Unit;

public class SimUnitsVBox extends VBox
{
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<SimFigurinesVBox> fig_boxes = new ArrayList<SimFigurinesVBox>();
	private int selected_unit = 0;
	private int column; // = 1 ou 3
	
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
		return buttons;
	}
	public ArrayList<SimFigurinesVBox> getFigBoxes(){
		return fig_boxes;
	}

	// afficher les unités de la liste déroulée 
	public void setList(int num_list)
	{
		ArrayList<Unit> units = AfficheSimulation.getSelectedList(num_list).getUnits();
		
		for(int i = 0; i < units.size(); i++)
		{
			VBox one_unit = new VBox();
			
			// chaque unité est un bouton pour dérouler des figurines
			Button tmp_button = new Button(units.get(i).getName());
			tmp_button.setMaxWidth(Double.MAX_VALUE);
			one_unit.getChildren().add(tmp_button);
			buttons.add(tmp_button);
			
			// créer une zone de figurines par unité
			SimFigurinesVBox one_fig_box = new SimFigurinesVBox(column, units.get(i));
			one_unit.getChildren().add(one_fig_box);
			fig_boxes.add(one_fig_box);
			
			this.getChildren().add(one_unit);
		}
	}
}

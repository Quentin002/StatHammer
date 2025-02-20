package vue.simulation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class SimFigurinesHBox extends TilePane // conteneur façon grille
{
	private boolean open = false; // les figurines sont cachées par défaut
	
	//public SimFigurinesHBox(){}
	
	public boolean isOpen(){
		return open;
	}
	
	public void changeState(){
		open ^= true; // inversion de bouléen avec un masque 00000001
	}
	
	
	public void setFigurines()
	{
		String[] figs_names = {"Figurine 1", "Figurine 2", "Figurine 3", "Figurine 4", "Figurine 5",
			"Figurine 6", "Figurine 7", "Figurine 8", "Figurine 9", "Figurine 10"};
		
		for(int i = 0; i< figs_names.length ;i++)
		{
			Image one_image = new Image("/images/android-fill.png");
			ImageView one_image_box = new ImageView();
			
			// dimensions
			one_image_box.setPreserveRatio(true);
			//one_image_box.setFitHeight(24); // appeler une des deux méthodes de dimensionnement
			one_image_box.setFitWidth(38);
			
			one_image_box.setImage(one_image);
			this.getChildren().add(one_image_box);
		}
	}
}

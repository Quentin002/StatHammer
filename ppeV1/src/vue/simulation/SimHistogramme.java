package vue.simulation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimHistogramme extends Pane{
	private Pane p;
	

	
//	public SimHistogramme() {
//		super();
//	}

	public static  void setHist(int[] tabdegat ,int[] tabmort, float degat_moyen ,float mort_moyen,Pane p) {
		Axis<String> xAxis = new CategoryAxis();
	       xAxis.setLabel("nbr mort");

	       NumberAxis yAxis = new NumberAxis();
	       yAxis.setLabel("pourcentage");
	       
	       Axis<String> xAxis2 = new CategoryAxis();
	       xAxis2.setLabel("nbr degat");

	       javafx.scene.chart.NumberAxis yAxis2 = new NumberAxis();
	       yAxis2.setLabel("pourcentage");

	       // Create a BarChart
	       BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
	       BarChart<String, Number> barChart2 = new BarChart<String, Number>(xAxis2, yAxis2);

	       // Series 1 - Data of 2014
	       XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
	       //dataSeries1.setName("mort moyen : "+mort_moyen);
	       for(int i=0;i<tabmort.length;i++) {
	    	   if(tabmort[i]!=0) {
	    		   dataSeries1.getData().add(new XYChart.Data<String, Number>(""+i , tabmort[i]/100));
	    	   }
	       }

	       //barChart.getData().add(dataSeries1);


	       // Series 1 - Data of 2014
	       XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
	       //dataSeries2.setName("degat moyen : "+degat_moyen);
	       for(int i=0;i<tabdegat.length;i++) {
	    	   if(tabdegat[i]!=0) {
	    		   dataSeries2.getData().add(new XYChart.Data<String, Number>(""+i , tabdegat[i]/100));
	    	   }
	       }

	       barChart.getData().add(dataSeries1);
	       barChart2.getData().add(dataSeries2);


	       
	       VBox vbox = new VBox();
	       vbox.getChildren().add(barChart);
	       vbox.getChildren().add(barChart2);
	       barChart.maxWidthProperty().bind(p.widthProperty());
	       barChart2.maxWidthProperty().bind(p.widthProperty());
	       barChart2.prefHeight(150);
	       barChart.prefHeight(150);
	       barChart.lookupAll(".default-color0.chart-bar")
        .forEach(n -> n.setStyle("-fx-bar-fill: red;"));
	       barChart2.lookupAll(".default-color0.chart-bar")
        .forEach(n -> n.setStyle("-fx-bar-fill: green;"));

	       
	       barChart.setMaxHeight(220);
	       barChart2.setMaxHeight(220);
	       barChart.prefHeight(200);
	       barChart.setLegendVisible(false);
	       barChart2.setLegendVisible(false);
	       barChart.setTitle("nbr mort moyen : "+mort_moyen);
	       barChart2.setTitle("nbr degat moyen : "+degat_moyen);
	       barChart2.prefHeight(200);
	       VBox.setVgrow(barChart, Priority.ALWAYS);
	   	   VBox.setVgrow(barChart2, Priority.ALWAYS);
	       //vbox.setSpacing(10);
	        
	       
	      p.getChildren().add(vbox);
	}

	
	

	//BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

}

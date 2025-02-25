package modele;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calcul {
	private int[] tabdegat1; 
	private float degat_moyen1; 
	private float mort_moyen1;
	private int[] tabmort1;
	
	

	
	public Calcul(int[] tabdegat1, float degat_moyen1, float mort_moyen1,int[] tabmort1) {
		super();
		this.tabdegat1 = tabdegat1;
		this.degat_moyen1 = degat_moyen1;
		this.mort_moyen1 = mort_moyen1;
		this.tabmort1 = tabmort1;
	}
	public int[] getTabdegat1() {
		return tabdegat1;
	}
	public void setTabdegat1(int[] tabdegat1) {
		this.tabdegat1 = tabdegat1;
	}
	public float getDegat_moyen1() {
		return degat_moyen1;
	}
	public void setDegat_moyen1(float degat_moyen1) {
		this.degat_moyen1 = degat_moyen1;
	}
	public float getMort_moyen1() {
		return mort_moyen1;
	}
	public void setMort_moyen1(float mort_moyen1) {
		this.mort_moyen1 = mort_moyen1;
	}
	public int[] getTabmort1() {
		return tabmort1;
	}
	public void setTabmort1(int[] tabmort1) {
		this.tabmort1 = tabmort1;
	}
	public static boolean jet_de_touche(boolean jet,int CT){
		int d1 = (int) ((Math.random() * (6 - 1)) + 1);
				switch(d1){
					case 1 :
						jet=false;
						break;
					case 6 :
						jet=true;
						break;
					default :
						if( d1  >= CT){
							jet=true;
						}
				}
				//System.out.println(d1);
				return jet;
			}
	public static boolean jet_de_blessure (boolean jet,int fo,int end){
		int d2 = (int) ((Math.random() * (6 - 1)) + 1);
		switch(d2){
			case 1 :
				jet=false;
				break;
			case 6 :
				jet=true;
				break;
			default :
				if(fo>2*end) {
					if(d2>=2) {
						jet = true;
					}
				}
				if(fo>end && fo<2*end) {
					if(d2>=3) {
						jet = true;
					}
				}
				if(fo==end) {
					if(d2>=4) {
						jet = true;
					}
				}
				if(fo>0.5*end && fo<end) {
					if(d2>=5) {
						jet = true;
					}
				}
				if(fo<=0.5*end) {
					if(d2>=6) {
						jet = true;
					}
				}

			}
		//System.out.println(d2);
		return jet;
		}
	public static boolean jet_de_sauvegarde (boolean block,int pa,int sv){
		int d3 = (int) ((Math.random() * (6 - 1)) + 1);
		switch(d3) {
				case 1:
					block = false;
				default :
					if ( d3 - pa < sv ){
						block = false;
					}
		}
		//System.out.println(d3);
		return block;
	}
	
	public static void combat(Figurine f1,Figurine f2,int z){
		int touche = 0;
		int sum_degat = 0;
		int blessure = 0;
		int degat_block = 0;
		for(int i=0 ;i<f1.getList().get(z).getA();i++ ){
			boolean jet1=false;
			boolean jet2=false;
			boolean block=true;
			jet1=jet_de_touche(jet1,f1.getList().get(z).getC() );
			//apptitude relancer
				if(jet1==true){
					touche = touche + 1;
					jet2=jet_de_blessure(jet2,f1.getList().get(z).getF(),f2.getE());
					//apptitude relancer
				}
				if(jet2==true){
					blessure = blessure + 1;
					block=jet_de_sauvegarde(block,f1.getList().get(z).getPa(),f2.getSv());
					//apptitude relancer
					//apptitude insensible_douleur
					if(block==false && f2.getHP() >0){
						f2.setHP(f2.getHP() - f1.getList().get(z).getD())  ;
						sum_degat = sum_degat +f1.getList().get(z).getD();
					}
					if(block == true) {
						degat_block = degat_block + 1;
					}
				}
			}
		System.out.println("nbr touche :"+touche);
		System.out.println("nbr blessure :"+blessure);
		System.out.println("degat bloquer :"+degat_block);
		System.out.println("somme degat :"+sum_degat);
	}		


	public static Calcul bataille(modele.Unit u1, modele.Unit u2){
		int touche = 0;
		int blessure = 0;
		int degat_block = 0;
		int complet_degat = 0;
		float degat_moyen;
		float mort_moyen = 0;
		boolean dflag = false;
		int[] tabdegat = new int[50];
		
		int[] tabmort = new int[50];
		for (int k=0;k<10000;k++){ //simulation 100000
			int sum_degat = 0;
			int adv = 0;
			int pvE=u2.getList_unit().get(adv).getHP();
			int mort = 0;
			for (int j=0; j<u1.getList_unit().size();j++){ // pour chaque figurine
				for(int i=0 ;i<u1.getList_unit().get(j).getList().get(1).getA();i++ ){ //nbr attq par arme
					dflag = false;
					boolean jet1=false;
					boolean jet2=false;
					boolean block=true;
					jet1=jet_de_touche(jet1,u1.getList_unit().get(j).getList().get(1).getC() );
					//apptitude relancer
						if(jet1==true){
							touche = touche + 1;
							jet2=jet_de_blessure(jet2,u1.getList_unit().get(j).getList().get(1).getF(),u2.getList_unit().get(adv).getE());
							//apptitude relancer
						}
						if(jet2==true){
							blessure = blessure + 1;
							block=jet_de_sauvegarde(block,u1.getList_unit().get(j).getList().get(1).getPa(),u2.getList_unit().get(adv).getSv());
							//apptitude relancer
							//apptitude insensible_douleur
							if(block==false && pvE >0){
								pvE=pvE - u1.getList_unit().get(j).getList().get(1).getD()  ;
								sum_degat = sum_degat + u1.getList_unit().get(j).getList().get(1).getD();
								complet_degat = complet_degat+u1.getList_unit().get(j).getList().get(1).getD();
								dflag = true;
							}
							if(block == true) {
								degat_block = degat_block + 1;
							}
							if(pvE  <=0 && dflag == true ) {
								mort = mort+1;
								mort_moyen = mort_moyen+1;
							}
						}
					}
					if(pvE  <=0 ) {
					if (adv+1<u2.getList_unit().size()){	 
			            adv=adv+1;
			            pvE=u2.getList_unit().get(adv).getHP();
			     
					}
				}
			}
			tabdegat[sum_degat] ++;
			tabmort[mort] ++;
		}
		degat_moyen =(float)complet_degat/10000;
		mort_moyen = (float)mort_moyen/10000;
		Calcul c1 = new Calcul(tabdegat,degat_moyen,mort_moyen,tabmort);
		return c1;
		
		

	       
	}

}

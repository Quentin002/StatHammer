package Model;

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
		int d1 = (int) ((Math.random() * (7 - 1)) + 1);
		//System.out.println(d1);
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
				//System.out.println("d1 : "+d1);
				return jet;
			}
	public static boolean jet_de_blessure (boolean jet,int fo,int end){
		int d2 = (int) ((Math.random() * (7 - 1)) + 1);
		//System.out.println(d2);
		switch(d2){
			case 1 :
				jet=false;
				break;
			case 6 :
				
				jet=true;
				break;
			default :
				//System.out.println(" test1 ");
				if(fo >2*end) {
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
				if(fo <=0.5*(float)end) {
					if(d2>=6) {
						jet = true;
					}
				}

			}
		//System.out.println("d2 : "+jet+" "+d2);
		//jet = true;
		return jet;
		}
	public static int chgt_a (String a){
		int attaque =0;
		int d1 = (int) ((Math.random() * (7 - 1)) + 1);
		int d2 = (int) ((Math.random() * (4 - 1)) + 1);
		switch(a) {
				case "D6":
					attaque = d1;
					break;
				case "D6+1":
					attaque = d1+1;
					//System.out.println("test");
					break;
				case "D6+2":
					attaque = d1+2;
					break;
				case "D6+3":
					attaque = d1+3;
					break;
				case "D6+4":
					attaque = d1+4;
					break;
				case "D3":
					attaque = d2;
					break;
				default :
					attaque = Integer.parseInt(a);
		}
		return attaque;
	}
	
	public static int chgt_d (String d){
		int degat =0;
		int d1 = (int) ((Math.random() * (7 - 1)) + 1);
		int d2 = (int) ((Math.random() * (4 - 1)) + 1);
		int d3 = (int) ((Math.random() * (9 - 1)) + 1);
		switch(d) {
				case "D8":
					degat = d3;
					//System.out.println("test");
					break;
				case "D6":
					degat = d1;
					break;
				case "D6+1":
					degat = d1+1;
					break;
				case "D6+2":
					degat = d1+2;
					break;
				case "D6+3":
					degat = d1+3;
					break;
				case "D6+4":
					degat = d1+4;
					break;
				case "D3":
					degat = d2;
					break;
				default :
					degat = Integer.parseInt(d);
		}
		return degat;
	}
	
	public static boolean jet_de_sauvegarde (boolean block,int pa,int sv){
		int d3 = (int) ((Math.random() * (7 - 1)) + 1);
		switch(d3) {
				case 1:
					block = false;
				default :
					if ( d3 + pa < sv ){
						block = false;
					}
		}
		//System.out.println("d3 : "+d3);
		//block=false;
		return block;
	}
			

	public static Calcul bataille(Unit u1, Unit u2){
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
			int pvE=u2.getFigurines().get(adv).getHP();
			int mort = 0;
			for (int j=0; j<u1.getFigurines().size();j++){ // pour chaque figurine
				if(u1.getFigurines().get(j).getHP()>0) {
					int attaque = chgt_a(u1.getFigurines().get(j).getSelectedWeapon().getA());
					while(pvE  <=0 && adv+1<u2.getFigurines().size()) {	 
			            adv=adv+1;
			            pvE=u2.getFigurines().get(adv).getHP();
			     
					}
					for(int i=0 ;i<attaque;i++ ){ //nbr attq par arme
						dflag = false;
						boolean jet1=false;
						boolean jet2=false;
						boolean block=true;
						//System.out.println("F : "+u1.getList_unit().get(j).ees().get(1).getF()*u2.getList_unit().get(adv).getE());
						//System.out.println("E : "+u2.getList_unit().get(adv).getE());
	
						jet1=jet_de_touche(jet1,(u1.getFigurines().get(j).getSelectedWeapon().getC()));
	
						//apptitude relancer
							if(jet1==true){
								touche = touche + 1;
								jet2=jet_de_blessure(jet2,u1.getFigurines().get(j).getSelectedWeapon().getF(),u2.getFigurines().get(adv).getE());
								//apptitude relancer
							}
							if(jet2==true){
								blessure = blessure + 1;
								block=jet_de_sauvegarde(block,u1.getFigurines().get(j).getSelectedWeapon().getPA(),u2.getFigurines().get(adv).getSV());
								//apptitude relancer
								//apptitude insensible_douleur
								if(block==false && pvE >0){
									int degat= chgt_d((u1.getFigurines().get(j).getSelectedWeapon().getD()));
									
									pvE=pvE - degat  ;
									if(pvE<0) {
										sum_degat = sum_degat + degat + pvE;
										complet_degat = complet_degat +degat +pvE;
									}
									else {
										sum_degat = sum_degat + degat;
										complet_degat = degat+complet_degat;
									}
									
	
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
					}
					if(pvE  <=0 ) {
					if (adv+1<u2.getFigurines().size()){	 
			            adv=adv+1;
			            pvE=u2.getFigurines().get(adv).getHP();
			     
					}
				}
			}
			tabdegat[sum_degat] ++;
			tabmort[mort] ++;
		}
		
		degat_moyen =(float)complet_degat/10000;
		mort_moyen = (float)mort_moyen/10000;
		System.out.println("nbr touche :"+touche);
		System.out.println("nbr blessure :"+blessure);
		System.out.println("degat bloquer :"+degat_block);
		System.out.println("degat moyen : "+ degat_moyen);
		System.out.println("mort moyen : "+mort_moyen);
		for(int i=0;i<50;i++) {
			if(tabdegat[i]!=0) {
				System.out.println(tabdegat[i]);
			}
		}
		Calcul c1 = new Calcul(tabdegat,degat_moyen,mort_moyen,tabmort);
		return c1;	       
	}

}


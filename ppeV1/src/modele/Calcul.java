package modele;

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
	
	// getter set setter
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
	
	// 1er jet de dé : jet pour toucher une figurine , résultat en fonction de CC ou CT
	public static boolean jet_de_touche(boolean jet,int C){
		// simulation lancer de dé
		int d1 = (int) ((Math.random() * (7 - 1)) + 1);
		switch(d1){
			// si 1 echec critique
			case 1 :
				jet=false;
				break;
			// si 6 reussite critique
			case 6 :
				jet=true;
				break;
			// comparaison du dé avec CC ou CT
			default :
				if( d1  >= C){
					jet=true;
				}
		}
		// return booléen pour continuer ou non attaque
		return jet;
	}
	
	// 2eme jet de dé : jet pour blesser une figurine , résultat en fonction de force de fig attaquante et de endurance de figurine attaquée
	public static boolean jet_de_blessure (boolean jet,int fo,int end){
		// simulation lancer de dé
		int d2 = (int) ((Math.random() * (7 - 1)) + 1);
		
		switch(d2){
			// si 1 echec critique
			case 1 :
				jet=false;
				break;
			// si 6 reussite critique	
			case 6 :	
				jet=true;
				break;
			default :
			//Comparaison entre force et endurance pour savoir si attaque blesse
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
		// return booléen pour continuer ou non attaque
		return jet;
		}
	
	// fonction pour determiner le nbr d'attaque d'une arme
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
	
	// fonction pour determiner le nbr de dégats d'une arme
	public static int chgt_d (String d){
		int degat =0;
		int d1 = (int) ((Math.random() * (7 - 1)) + 1);
		int d2 = (int) ((Math.random() * (4 - 1)) + 1);
		int d3 = (int) ((Math.random() * (9 - 1)) + 1);
		switch(d) {
				case "D8":
					degat = d3;
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
	
	// 2eme jet de dé : jet de sauvegarde , pour savoir si l'armure d'une figurine la protège d'une blessure
	public static boolean jet_de_sauvegarde (boolean block,int pa,int sv){
		// simulation du jet de dé
		int d3 = (int) ((Math.random() * (7 - 1)) + 1);
		switch(d3) {
				//echec critique la figurine adverse n'a pas réussie à se protéger
				case 1:
					block = false;
				default :
					//comparaison entre pénétration d'armure et sauvegarde
					if ( d3 + pa < sv ){
						block = false;
					}
		}
		//// return booléen pour finaliser ou non attaque
		return block;
	}
			
	// fonction simulant des battailes entre 2 unités
	public static Calcul bataille(modele.Unit u1, modele.Unit u2){
		
		// Déclaration des variables

		int complet_degat = 0;
		float degat_moyen;
		float mort_moyen = 0;
		boolean dflag = false;
		int[] tabdegat = new int[50];
		int[] tabmort = new int[50];
		
		// boucle simulant 10 000 batailles
		for (int k=0;k<10000;k++){ 
			
			int sum_degat = 0;
			int adv = 0;
			int pvE=u2.getFigurines().get(adv).getHP();
			int mort = 0;
			
			// boucle parcourant les figurines d'une unité
			for (int j=0; j<u1.getFigurines().size();j++){ 
				// si figurine non morte 
				if(u1.getFigurines().get(j).getHP()>0) {
					int attaque = chgt_a(u1.getFigurines().get(j).getSelectedWeapon().getA());
					
					// Condition permettant d'attaquer une nouvelle figurine
					while(pvE  <=0 && adv+1<u2.getFigurines().size()) {	 
			            adv=adv+1;
			            pvE=u2.getFigurines().get(adv).getHP();
			     
					}
					
					// boucle correspondant au nbr d'attaques par armes
					for(int i=0 ;i<attaque;i++ ){
						dflag = false;
						boolean jet1=false;
						boolean jet2=false;
						boolean block=true;

						// fonction pour jet de touche
						jet1=jet_de_touche(jet1,(u1.getFigurines().get(j).getSelectedWeapon().getC()));
							
							// si attaque touche
							if(jet1==true){
								// fonction pour jet de blessure
								jet2=jet_de_blessure(jet2,u1.getFigurines().get(j).getSelectedWeapon().getF(),u2.getFigurines().get(adv).getE());
							}
							
							// si blessure 
							if(jet2==true){
								block=jet_de_sauvegarde(block,u1.getFigurines().get(j).getSelectedWeapon().getPA(),u2.getFigurines().get(adv).getSV());
								
								// si jet de sauvegarde echec et fig adverse à des HP
								if(block==false && pvE >0){
									
									int degat= chgt_d((u1.getFigurines().get(j).getSelectedWeapon().getD()));
									pvE=pvE - degat  ;
									
									// Calcul des dégats effectués par une attaque
									if(pvE<0) {
										sum_degat = sum_degat + degat + pvE;
										complet_degat = complet_degat +degat +pvE;
									}
									else {
										sum_degat = sum_degat + degat;
										complet_degat = degat+complet_degat;
									}
									
									// booléen pour indiquer une mort possible
									dflag = true;
								}
								
								// compteur du nbr de mort
								if(pvE  <=0 && dflag == true ) {
									mort = mort+1;
									mort_moyen = mort_moyen+1;
								}
							}
						}
					}
					// si fig mort changement de prochaine cible
					if(pvE  <=0 ) {
					if (adv+1<u2.getFigurines().size()){	 
			            adv=adv+1;
			            pvE=u2.getFigurines().get(adv).getHP();
			     
					}
				}
			}
			// compteurs des sommes des dégats et du nbr de mort par bataille
			tabdegat[sum_degat] ++;
			tabmort[mort] ++;
		}
		
		//calcul des degat et mort moyen
		degat_moyen =(float)complet_degat/10000;
		mort_moyen = (float)mort_moyen/10000;

		// Instanciation de Calucl (données pour histogramme ) 
		Calcul c1 = new Calcul(tabdegat,degat_moyen,mort_moyen,tabmort);
		return c1;	       
	}

}


package modele;

import java.util.ArrayList;

public abstract class  Arme {
	
	private String nom;
	private ArrayList<AptitudeArme> aptitudes;
	private String A;
	private int F;
	private int PA;
	private String D;
	public Arme(String nom, ArrayList<AptitudeArme> aptitudes, String a, int f, int pA, String d) {
		
		this.nom = nom;
		this.aptitudes = aptitudes;
		A = a;
		F = f;
		PA = pA;
		D = d;
	}
	public ArrayList<Integer> statsAtk(){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		
		liste.add(F);
		liste.add(PA);
		
		return liste;
	}
		
	
}

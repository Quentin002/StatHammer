package modele;

import java.util.ArrayList;

public abstract class  Arme {
	
	private String nom;
	private ArrayList<AptitudeArme> aptitudes;
	private int A;
	private int F;
	private int PA;
	private int D;
	public Arme(String nom, ArrayList<AptitudeArme> aptitudes, int a, int f, int pA, int d) {
		
		this.nom = nom;
		this.aptitudes = aptitudes;
		A = a;
		F = f;
		PA = pA;
		D = d;
	}
	public ArrayList<Integer> statsAtk(){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		liste.add(A);
		liste.add(F);
		liste.add(PA);
		liste.add(D);
		return liste;
	}
		
	
}

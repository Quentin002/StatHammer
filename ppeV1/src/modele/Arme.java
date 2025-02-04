package modele;

import java.util.ArrayList;

public abstract class  Arme {
	String nom;
	ArrayList<AptitudeArme> aptitudes;
	int A;
	int F;
	int PA;
	int D;
	public Arme(String nom, ArrayList<AptitudeArme> aptitudes, int a, int f, int pA, int d) {
		super();
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

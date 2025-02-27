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
	

	public Arme(String nom, int F, int PA, int A, int D) {
			this.nom = nom;
			this.F = F;
			this.PA = PA;
			this.A=A;
			this.D=D;
	 }


	public ArrayList<Integer> statsAtk(){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		liste.add(A);
		liste.add(F);
		liste.add(PA);
		liste.add(D);
		return liste;
	}
	
	//v2
	public int getD() {
		return D;
	}

	public void setD(int d) {
		this.D = d;
	}

	public String getNom() {
		return nom;
	}

	public int getA() {
		return A;
	}

	public void setA(int a) {
		this.A = a;
	}

	public void setPa(int pa) {
		this.PA = pa;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPa() {
		return PA;
	}


	public int getF() {
		return F;
	}

	public void setF(int f) {
		this.F = f;
	}
	public abstract int getC();
	
}

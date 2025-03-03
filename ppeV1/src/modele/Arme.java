package modele;

import java.util.ArrayList;

public abstract class  Arme {
	
	private String nom;
	private ArrayList<AptitudeArme> aptitudes;
	private String portee;
	private String A;
	private int F;
	private int PA;
	private String D;
	public Arme(String nom, ArrayList<AptitudeArme> aptitudes,String portee, String a, int f, int pA, String d) {
		
		this.nom = nom;
		this.aptitudes = aptitudes;
		this.portee=portee;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<AptitudeArme> getAptitudes() {
		return aptitudes;
	}
	public void setAptitudes(ArrayList<AptitudeArme> aptitudes) {
		this.aptitudes = aptitudes;
	}
	public String getPortee() {
		return portee;
	}
	public void setPortee(String portee) {
		this.portee = portee;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public int getF() {
		return F;
	}
	public void setF(int f) {
		F = f;
	}
	public int getPA() {
		return PA;
	}
	public void setPA(int pA) {
		PA = pA;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
		
	
}

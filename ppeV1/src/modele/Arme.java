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
	public Arme(String nom, ArrayList<AptitudeArme> aptitudes,String portee, String a, int f, int pA, String d)
	{		
		this.nom = nom;
		this.aptitudes = aptitudes;
		this.portee=portee;
		this.setAptitudes(aptitudes);

		A = a;
		F = f;
		PA = pA;
		D = d;
	}
	public Arme(String nom, int F, int PA, String A, String D) {
		this.nom = nom;
		this.F = F;
		this.PA = PA;
		this.A=A;
		this.D=D;
 }
	public ArrayList<Integer> statsAtk(){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		
		liste.add(F);
		liste.add(PA);
		
		return liste;
	}
	
	//v2
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	
	public abstract int getC();

	public ArrayList<AptitudeArme> getAptitudes() {
		return aptitudes;
	}
	public void setAptitudes(ArrayList<AptitudeArme> aptitudes) {
		this.aptitudes = aptitudes;
	}	
}
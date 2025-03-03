package modele;

import java.util.ArrayList;

public class ArmeMelee extends Arme {
	private int CC;

	public ArmeMelee(String nom, ArrayList<AptitudeArme> aptitudes,String portee, String a, int f, int pA, String d,int cc) {
		super(nom, aptitudes,portee, a, f, pA, d);
		this.CC=cc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Integer> statsAtk() {
		ArrayList<Integer> liste = super.statsAtk();
		liste.add(CC);
		return null;
	}

	public int getCC() {
		return CC;
	}

	public void setCC(int cC) {
		CC = cC;
	}

	//public int getC() {}
}


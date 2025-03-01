package modele;

import java.util.ArrayList;

public class ArmeDist extends Arme {
	private int CT;
	public ArmeDist(String nom, ArrayList<AptitudeArme> aptitudes, int a, int f, int pA, int d,int ct) {
		super(nom, aptitudes, a, f, pA, d);
		this.CT=ct;
		// TODO Auto-generated constructor stub
	}
	@Override
	public ArrayList<Integer> statsAtk() {
		// TODO Auto-generated method stub
		ArrayList<Integer> liste = super.statsAtk();
		liste.add(CT);
		return liste;
	}
	
	public int getC() {
		return CT;
	}
	
}
package modele;

import java.util.ArrayList;

public class ArmeMelee extends Arme {
	private int CC;

	public ArmeMelee(String nom, ArrayList<AptitudeArme> aptitudes, int a, int f, int pA, int d,int cc) {
		super(nom, aptitudes, a, f, pA, d);
		this.CC=cc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Integer> statsAtk() {
		ArrayList<Integer> liste = super.statsAtk();
		liste.add(CC);
		return null;
	}

	@Override
	protected int getC() {
		return CC;
	}

}

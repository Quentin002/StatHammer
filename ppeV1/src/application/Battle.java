package application;

import modele.Armee;
import modele.ArmeeListe;
import modele.Unit;

/* mémoire des actions de l'utilisateurs, liste, unités, PV, armes, aptitudes */
public class Battle
{
	private ArmeeListe selected_list1;
	private ArmeeListe selected_list2;
	private ArmeeListe attaquant;
	private ArmeeListe defenseur;
	private Armee army1;
	private Armee army2;
	private int index_selected_unit1 = -1;
	private int index_selected_unit2 = -1;
	
	// getters/setters
	public ArmeeListe getSelectedList(int nb) {
		return (nb == 1 ? selected_list1 : selected_list2);
	}
	public void setSelectedList(int nb, ArmeeListe list) {
		if(nb == 1) {
			selected_list1 = list;
		}
		else if(nb == 2) {
			selected_list2 = list;
		}
		else {
			System.out.println("le paramètre nb doit valoir 1 ou 2");
		}
	}
	public Armee getArmy(int nb) {
		return (nb == 1 ? army1 : army2);
	}
	public void setArmy(int nb, Armee a) {
		if(nb == 1) {
			army1 = a;
		}
		else if(nb == 2) {
			army2 = a;
		}
		else {
			System.out.println("le paramètre nb doit valoir 1 ou 2");
		}
	}
	public Unit getSelectedUnit(int nb) {
		return (nb == 1 ? selected_list1.getUnits().get(index_selected_unit1) : selected_list2.getUnits().get(index_selected_unit2));
	}
	public int getSelectedUnitIndex(int nb) {
		return (nb == 1 ? index_selected_unit1 : index_selected_unit2);
	}
	public void setSelectedUnit(int nb, int unit_list_index) {
		if(nb == 1) {
			index_selected_unit1 = unit_list_index;
		}
		else if(nb == 2) {
			index_selected_unit2 = unit_list_index;
		}
		else {
			System.out.println("le paramètre nb doit valoir 1 ou 2");
		}
	}
}

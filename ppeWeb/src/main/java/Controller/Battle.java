package Controller;

import java.lang.reflect.Field;
import Model.Arme;
import Model.Armee;
import Model.ArmeeListe;
import Model.Unit;

/* mémoire des actions de l'utilisateurs, liste, unités, PV, armes, aptitudes */
public class Battle
{
	static private ArmeeListe selected_list1 = null;
	static private ArmeeListe selected_list2 = null;
	static private Armee army1;
	static private Armee army2;
	static private int index_selected_unit1 = -1; // commencent à 0
	static private int index_selected_unit2 = -1;
	
	// getters/setters
	static public ArmeeListe getSelectedList(int nb) {
		return (nb == 1 ? selected_list1 : selected_list2);
	}
	static public void setSelectedList(int nb, ArmeeListe list) {
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
	static public Armee getArmy(int nb) {
		return (nb == 1 ? army1 : army2);
	}
	static public void setArmy(int nb, Armee a) {
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
	static public Unit getSelectedUnit(int nb) {
		return (nb == 1 ? selected_list1.getUnits().get(index_selected_unit1) : selected_list2.getUnits().get(index_selected_unit2));
	}
	static public int getSelectedUnitIndex(int nb) {
		return (nb == 1 ? index_selected_unit1 : index_selected_unit2);
	}
	static public void setSelectedUnit(int nb, int unit_list_index) {
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
	static public Arme getSeletedWeapon(int nb, String group_name) {
		return getSelectedUnit(nb).getIdenticalFigsGroups().get(group_name).get(0).getSelectedWeapon();
	}
	static public void reverseArmies()
	{
		Field[] fields = selected_list1.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object temp = field.get(selected_list1);
                field.set(selected_list1, field.get(selected_list2));
                field.set(selected_list2, temp);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
	}
	static public void clean() {
		selected_list1 = null;
		selected_list2 = null;
		army1 = null;
		army2 = null;
		index_selected_unit1 = -1;
		index_selected_unit2 = -1;
	}
}

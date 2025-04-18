package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Armee;
import Model.ArmeeListe;
import Model.Faction;
import Model.Unit;

public class StockageCreerListe {
	
	//sauvegarde les nom des armée, des unités et des factions pour ne pas refaire appel à la BDD en boucle
	
	private static final HashMap<String,Faction> faction = new HashMap<String, Faction>();
	private static final HashMap<String,Armee> armee = new HashMap<String, Armee>();
	private static final HashMap<String,Unit> unit = new HashMap<String, Unit>();
	private static ArmeeListe armeeListe;
	
	
	public static void initFaction() {
		faction.clear();
		
		for(Faction fac : Instanciation.getFaction()) {
			faction.put(fac.getNom(), fac);
		}
	}
	
	public static ArrayList<String> getNomFac(){
		ArrayList<String> rendu = new ArrayList<String>();
		for(Faction fac :faction.values() ) {
			rendu.add(fac.getNom());
		}
		return rendu;
	}
	
	public static void initArmee(String nomFaction) {
		armee.clear();
		
		 for(Armee arm:Instanciation.getArmee(faction.get(nomFaction))) {
			 armee.put(arm.getName(), arm);
		 }
	}

	public static ArrayList<String> getNomArmee() {
		ArrayList<String> rendu = new ArrayList<String>();
		
		for(String arm : armee.keySet()) {
			rendu.add(arm);
		}
		//rendu.add(rendu.removeFirst());
		return rendu;
	}
	public static void initUnit(String nomArmee) {
		unit.clear();
		
		for(Unit uni:Instanciation.getUniteOfArmy(armee.get(nomArmee))) {
			unit.put(uni.getName(), uni);
		}
	}
	public static ArrayList<String> getNomUnit() {
		ArrayList<String> rendu = new ArrayList<String>();
		for(Unit uni : unit.values()) {
			rendu.add(uni.getName());
		}
		
		return rendu;
	}
	public static void initArmeeListe() {
		armeeListe = new ArmeeListe(new ArrayList<Unit>(),"nom_temp","desc","data");
	}
	public static ArmeeListe getArmeeListe() {
		return armeeListe;
	}
	public static Unit getUnit(String nomUnit) {
		return unit.get(nomUnit);
	}
}

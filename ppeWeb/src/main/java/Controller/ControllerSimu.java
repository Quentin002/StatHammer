package Controller;
import Model.Aptitude;
import Model.Arme;
import Model.ArmeeListe;
import Model.Figurine;
import Model.Unit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Servlet implementation class ControllerSimu
 */
@WebServlet("/ControllerSimu")
public class ControllerSimu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerSimu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null) {
            response.sendRedirect("connexion");
        }
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// récupération du JSON dans une chaine
		StringBuilder jsonBuffer = new StringBuilder();
	    String line;
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null) {
	        jsonBuffer.append(line);
	    }
	    String json = jsonBuffer.toString();
	    
	    HashMap<String, String> data = basicParseJson(json);
	    int col = data.get("col") != null ? Integer.valueOf(data.get("col")): 0; // 1 ou 2
	    int group;
	    ArrayList<Figurine> fig_group;
	    int alive_figs;
	    
		switch(data.get("action")) {
			case "make_units_box":
				int id_list = Integer.valueOf(data.get("list")); // id BDD
				
				// récupération des unités en BDD
				ArrayList<ArmeeListe> listes = (ArrayList<ArmeeListe>) session.getAttribute("listes");
				
				for(ArmeeListe one_list : listes) {
					if(one_list.getId() == id_list) {
						// optimisation
						if(one_list.getUnits().size() == 0) {
							Instanciation.getUnitsOfAList(one_list);
						}
						Battle.setSelectedList(col, one_list);
						ArrayList<Unit> units_list = Battle.getSelectedList(col).getUnits();
						Battle.setArmy(col, units_list.get(0).getArmee());
						break;
					}
				}
				
				request.setAttribute("col", col);
			    request.setAttribute("listes", listes); // => units_box.jsp
			    request.setAttribute("units_list", Battle.getSelectedList(col).getUnits());
			    request.getRequestDispatcher("units_box.jsp").forward(request, response);
				break;
			case "get_army_file":
				out.println(Battle.getSelectedList(col).getUnits().get(0).getArmee() + ".png");
				break;
			case "set_selected_unit":
				int unit_index = Integer.valueOf(data.get("unit"));
				Battle.setSelectedUnit(col, unit_index);
				out.println("success");
				break;
			case "make_weapons_aptitudes_zone":
				group = Integer.valueOf(data.get("group").substring(5, data.get("group").length())); // int <- groupX
				fig_group = Battle.getSelectedUnit(col).getIdenticalFigsGroups()
					.get(Battle.getSelectedUnit(col).getIdenticalFigsGroupsKeys().get(group)); // ArrayList<Figurine>
				alive_figs = 0;
				for(Figurine fig : fig_group) {
					if(fig.getHP() > 0) {
						alive_figs++;
					}
				}
				
				request.setAttribute("fig_group", fig_group);
				request.setAttribute("group", group);
				request.setAttribute("alive_figs", alive_figs);
				request.getRequestDispatcher("weapons_and_aptitudes.jsp").forward(request, response);
				break;
			case "set_weapon":
				group = Integer.valueOf(data.get("group").substring(5, data.get("group").length())); // int <- groupX
				fig_group = Battle.getSelectedUnit(col).getIdenticalFigsGroups() // ArrayList<Figurine>
					.get(Battle.getSelectedUnit(col).getIdenticalFigsGroupsKeys().get(group));
				Arme weapon = fig_group.get(0).getArmes().get(Integer.valueOf(data.get("weapon")));
				
				String stats = "{\"A\": \""	+ weapon.getA() + "\","
					+ " \"F\": \""	+ weapon.getF() + "\","
					+ "\"PA\": \""	+ weapon.getPA() + "\","
					+ "\"D\": \""	+ weapon.getD() + "\","
					+ "\"portée\": \""	+ weapon.getPortee().replace("\"", "\\\"") + "\"}";
				out.println(stats);
				break;
			case "setAptitude":
				group = Integer.valueOf(data.get("group").substring(5, data.get("group").length())); // int <- groupX
				fig_group = Battle.getSelectedUnit(col).getIdenticalFigsGroups() // ArrayList<Figurine>
					.get(Battle.getSelectedUnit(col).getIdenticalFigsGroupsKeys().get(group));
				ArrayList<Aptitude> aptitudes = fig_group.get(0).getAptitudes();
				
				// cocher/décocher la case => ajouter ou retirer une aptitude de la liste
				//
				
				out.println("success");
				break;
			case "set_figs_group_hp":
				group = Integer.valueOf(data.get("group").substring(5, data.get("group").length()));
				alive_figs = Integer.valueOf(data.get("alive_figs"));
				
				// groupe de figurines identiques
				fig_group = Battle.getSelectedUnit(col).getIdenticalFigsGroups()
					.get(Battle.getSelectedUnit(col).getIdenticalFigsGroupsKeys().get(group));
				
				for(int i = 0; i < alive_figs; i++) {
					fig_group.get(i).setHP(fig_group.get(i).getHPMax());
				}
				for(int i = alive_figs; i < fig_group.size(); i++) {
					fig_group.get(i).setHP(0);
				}
				
				out.println(fig_group.get(0).getHPMax()); // utile pour selectNumberOfWeapons()
				break;
			case "set_one_fig_hp":
				String[] markup_id = data.get("fig_div_id").split("_"); // ["groupY", "figZ"]
				group = Integer.valueOf(markup_id[0].substring(5, markup_id[0].length()));
				int fig = Integer.valueOf(markup_id[1].substring(3, markup_id[1].length()));
				int hp = Integer.valueOf(data.get("hp"));
				
				Figurine figurine = Battle.getSelectedUnit(col).getIdenticalFigsGroups()
					.get(Battle.getSelectedUnit(col).getIdenticalFigsGroupsKeys().get(group))
					.get(fig);
				figurine.setHP(hp);
				
				out.println("success");
				break;
			case "reverse_armies":
				Battle.reverseArmies();
				Battle.setSelectedUnit(1, -1);
				Battle.setSelectedUnit(2, -1);
				
				out.println("success");
				break;
			default:
				out.println("erreur, paramètre \"action\" non valide");
		}
	}
	
	private HashMap<String, String> basicParseJson(String json)
	{
		json = json.substring(1, json.length() - 1); // retirer les {} autour
		
	    String[] words = json.split(","); // 1er découpage en paires du type: "action":"make_units_box"
	    
	    ArrayList<String[]> pairs = new ArrayList<String[]>(); 
	    for(String word : words) {
	    	pairs.add(word.split(":")); // 2è découpage en tableaux de deux cases
	    }
	    
	    HashMap<String, String> data = new HashMap<String, String>();
	    for(String[] one_pair : pairs){
	    	one_pair[0] = one_pair[0].replace("\"", ""); // on retire les caractères "
	    	one_pair[1] = one_pair[1].replace("\"", "");
	    	data.put(one_pair[0], one_pair[1]); // et hop une hashmap!
	    }
	    return data;
	}
}

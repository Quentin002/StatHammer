package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	    
		switch(data.get("action")) {
			case "make_units_box":
				int col = Integer.valueOf(data.get("col"));
				int id_list = Integer.valueOf(data.get("list"));
				
				// récupération des unités en BDD
				
				// variables de la vue
				
				request.getRequestDispatcher("units_box.jsp").forward(request, response);
				break;
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

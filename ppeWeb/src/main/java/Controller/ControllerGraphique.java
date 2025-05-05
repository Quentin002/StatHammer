package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Model.Arme;
import Model.ArmeMelee;
import Model.Calcul;
import Model.Figurine;
import Model.Unit;


@WebServlet("/ControllerGraphique")
public class ControllerGraphique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerGraphique() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("key"));
		//out.println(request.getParameter("key"));


			
			ArmeMelee wt1 = new ArmeMelee("Marteau Thunder",8,-2,"3","2",4);
			ArmeMelee wt2 = new ArmeMelee("Épée tronçonneuse Astartes",4,-1,"5","1",3);
			ArrayList<Arme> lt1= new ArrayList<>();
			lt1.add(wt1);
			ArrayList<Arme> lt2= new ArrayList<>();
			lt2.add(wt2);
			
			Figurine ft1 = new Figurine("Frère d'Épée Primaris",4,3,3,lt1);
			Figurine ft2 = new Figurine("Frère d'Épée Primaris",4,3,3,lt1);
			Figurine ft3 = new Figurine("Castellan Frère d'Épée",4,3,3,lt2);
			Figurine ft4 = new Figurine("Castellan Frère d'Épée",4,3,3,lt2);
			
			ArrayList<Figurine> fut1 = new ArrayList<Figurine>();
			fut1.add(ft1);
			fut1.add(ft2);
			fut1.add(ft3);
			fut1.add(ft4);
			
			Unit u1 = new Unit("unite1",fut1);
			Unit u2 = new Unit("unite2",fut1);
			
			Calcul test =Calcul.bataille(u1,u2);
			
			
			request.setAttribute("table", test);
			
			
			//String param1 = request.getParameter("degat_moyen");
	        //String param2 = request.getParameter("param2");

	        // Traiter les paramètres
	        //System.out.println("Param1: " + param1);
	        //System.out.println("Param2: " + param2);

	        // Répondre au client
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        //response.getWriter().write("{\"html\" : \"" + out.pr + "\"}");
			
			
			request.getRequestDispatcher("Hist.jsp").forward(request, response);
			
			
	}

}

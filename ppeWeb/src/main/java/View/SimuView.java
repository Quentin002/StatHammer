package View;

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


@WebServlet("/SimuView")
public class SimuView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimuView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String titre = "Simulation";
	    String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
	
		
	    
		String body = "<h1>Simulation</h1>"
				+"<div class='container'>"
				
					+"<input type=submit class='buttonGcompte' onclick='GraphiqueSimu();' value='Envoyer'>"
					+"<script src='js/simu.js'></script>"
					+"<script src=\"https://cdn.canvasjs.com/canvasjs.min.js\"></script>"
					 +"<div id='graphique'>"
					+"</div>"
					+"</div>";
		

		
					String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
					out.println(html);
					//request.getRequestDispatcher("Hist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

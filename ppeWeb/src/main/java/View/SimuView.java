package View;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		HttpSession session = request.getSession(false);
	     if (session == null || session.getAttribute("nom") == null) {
	         response.sendRedirect("connexion");
	         return;
	     }
	     
	     ArrayList<Model.ArmeeListe> listes = (ArrayList<Model.ArmeeListe>)request.getAttribute("listes");
	     request.setAttribute("listes", listes);
	     
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String titre = "Simulation";
		String header_bottom =
				  "\r\n"
							+ "		</title>\r\n"
							+ "		<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css\">\r\n"
							+ "		<link rel=\"stylesheet\" href=\"css/main.css\">\r\n"
							+ "		<link rel=\"stylesheet\" href=\"css/simulation.css\"/>"
							+ "		<link rel=\"stylesheet\" href=\"css/anti-font-awesome.css\"/>"
							+ "		<script type=\"text/javascript\" src=\"js/Battle.js\"></script>"
							+ "		<script type=\"text/javascript\" src=\"js/simulation.js\"></script>"
							+ "		<script src='js/simu.js'></script>"
							+ "		<script src=\"js/graphique.js\"></script>\r\n"
							+ "		<script src=\"https://cdn.canvasjs.com/canvasjs.min.js\"></script>"
							+ "	</head>\r\n"
							+ "	<body>";
	    String header = ConnexionView.headerTop + titre + header_bottom;
	    out.println(header + AccueilView.barDeNav);
	    
	    ArrayList<Model.ArmeeListe> listes = (ArrayList<Model.ArmeeListe>) session.getAttribute("listes");
	    request.setAttribute("listes", listes); // => simulation.jsp
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("simulation.jsp");
        dispatcher.include(request, response);

        String footer = "    	<footer>\r\n"
        		+ "    		<div id=\"toast\"></div>\r\n"
        		+ "    	</footer>\r\n"
        		+ "    </body>\r\n"
        		+ "</html>";
		out.println(footer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

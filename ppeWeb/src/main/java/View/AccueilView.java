package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AccueilView
 */
@WebServlet("/AccueilView")
public class AccueilView extends HttpServlet {
	public static String barDeNav;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("ConnexionView");
        } 

        String boutonAdmin = "";
		String titre = "StatHammer : Accueil";

        if (session.getAttribute("role").equals("Admin")) {
        	boutonAdmin = "    <li><a href='BarDeNavController?action=admin'>Événements</a></li>\r\n";
        }
		
		response.setContentType("text/html; charset=UTF-8");


		String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
		
		barDeNav =
			    "<div class='barDeNav'>\r\n"
			  + "  <ul>\r\n"
			  + "    <li><a href='BarDeNavController?action=accueil'>Accueil</a></li>\r\n"
			  + boutonAdmin
			  + "    <li><a href='BarDeNavController?action=logout'>Déconnexion</a></li>\r\n"
			  + "  </ul>\r\n"
			  + "</div>\r\n";

		String body =
			  "<div class='container'>\r\n"
		  	+ "  </div>\r\n";

		
		String html = header + barDeNav + body + ConnexionView.footer;
		
		PrintWriter out = response.getWriter();
		out.println(html);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	    
	}

}

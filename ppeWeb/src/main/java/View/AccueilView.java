package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		String titre = "StatHammer : Accueil";

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// HEADER
		String header =
			"<!DOCTYPE HTML>\r\n"
		  + "<html>\r\n"
		  + "<head>\r\n"
		  + "  <meta charset=\"UTF-8\"/>\r\n"
		  + "  <title>" + titre + "</title>\r\n"
		  + "  <style>" + ConnexionView.css + "</style>\r\n"
		  + "</head>\r\n"
		  + "<body>\r\n";
		
		barDeNav =
			    "<div class='barDeNav'>\r\n"
			  + "  <ul>\r\n"
			  + "    <li><a href='HomeView'>Accueil</a></li>\r\n"
			  + "    <li><a href='AdminView'>Événements</a></li>\r\n"
			  + "    <li><a href='Logout'>Déconnexion</a></li>\r\n"
			  + "  </ul>\r\n"
			  + "</div>\r\n";

		String body =
			  "<div class='container'>\r\n"
		  	+ "  </div>\r\n";
		
		String footer =
			"</body>\r\n"
		  + "</html>";

		out.println(header + barDeNav + body + footer);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

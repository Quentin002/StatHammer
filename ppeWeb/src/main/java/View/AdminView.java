package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/AdminView")
@MultipartConfig
public class AdminView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminView() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("ConnexionView");
        } 
		
		response.setContentType("text/html; charset=UTF-8");

		String titre = "StatHammer : Admin";
		String action = "AdminController";
		
		// HEADER
		String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;

		
		// FORMULAIRE
		String body =
			  "<div class='container'>\r\n"
			+ "  <div class='left-panel'>\r\n"
			+ "   <form method='POST' action='" + action + "' enctype='multipart/form-data'>\r\n"
			+ "       <label for='nom'>Nom :</label><br/>\r\n"
			+ "       <input type='text' name='nom' required/><br/><br/>\r\n"

		  	+ "       <label for='desc'>Description :</label><br/>\r\n"
		  	+ "       <input type='text' name='desc' required/><br/><br/>\r\n"

		  	+ "       <label for='date'>Date :</label><br/>\r\n"
		  	+ "       <input type='date' name='date' required/><br/><br/>\r\n"

		  	+ "       <label for='image'>Image :</label><br/>\r\n"
		  	+ "       <input type='file' name='image' accept='image/*' required/><br/><br/>\r\n"

		  	+ "       <input type='submit' value='Créer évènement'/>\r\n"
		  	+ "   </form>\r\n"
		  	+ "  </div>\r\n"
		  	+ "</div>\r\n";
		
		String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
		
		PrintWriter out = response.getWriter();
		out.println(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}

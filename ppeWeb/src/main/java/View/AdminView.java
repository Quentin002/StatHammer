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
		String titre = "StatHammer : Admin";
		String action = "AdminController";

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

		
		// FORMULAIRE
		String body =
			  "<div class='container'>\r\n"
			+ "  <div class='left-panel'>\r\n"
			+ "    <h1>Créer un événement</h1>\r\n"
			+ "   <form method='POST' action='" + action + "' enctype='multipart/form-data'>\r\n"
			+ "       <label for='nom'>Nom :</label><br/>\r\n"
			+ "       <input type='text' name='nom' required/><br/><br/>\r\n"

		  	+ "       <label for='desc'>Description :</label><br/>\r\n"
		  	+ "       <input type='text' name='desc' required/><br/><br/>\r\n"

		  	+ "       <label for='date'>Date :</label><br/>\r\n"
		  	+ "       <input type='date' name='date' required/><br/><br/>\r\n"

		  	+ "       <label for='image'>Image :</label><br/>\r\n"
		  	+ "       <input type='file' name='image' accept='image/*' required/><br/><br/>\r\n"

		  	+ "       <input type='submit' value='Valider'/>\r\n"
		  	+ "   </form>\r\n"
		  	+ "  </div>\r\n"
		  	+ "</div>\r\n";
		
		String footer =
			"</body>\r\n"
		  + "</html>";

		out.println(header + AccueilView.barDeNav + body + footer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Si tu veux gérer un POST ici plus tard
		doGet(request, response);
	}
}

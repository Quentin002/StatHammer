package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		
		String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;

		
	     //StringBuilder permet de travailler avec la méthode ".append" pour assembler chaine de caracatère par chaine de caractère 
	     StringBuilder body = new StringBuilder();
	     body.append("<div class='container'>\r\n")
		     .append("  <div class='left-panel'>\r\n")
		     .append("   <form method='POST' action='" + action + "' enctype='multipart/form-data'>\r\n")
		     .append("       <label for='nom'>Nom :</label><br/>\r\n")
		     .append("       <input type='text' name='nom' required/><br/><br/>\r\n")
		     .append("       <label for='desc'>Description :</label><br/>\r\n")
		     .append("       <input type='text' name='desc' required/><br/><br/>\r\n")
		     .append("       <label for='date'>Date :</label><br/>\r\n")
		     .append("       <input type='date' name='date' required/><br/><br/>\r\n")
		     .append("       <label for='image'>Image :</label><br/>\r\n")
		     .append("       <input type='file' name='image' accept='image/*' required/><br/><br/>\r\n")
		     .append("       <input type='submit' value='Créer évènement'/>\r\n")
		     .append("   </form>\r\n")
		     .append("  </div>\r\n");
	     
	     body.append("  <div class='right-panel'>\r\n");
	     
	     @SuppressWarnings("unchecked")
	     ArrayList<String[]> evenements = (ArrayList<String[]>) session.getAttribute("evenements");
	     
	     for (int i = 0; i < evenements.size(); i++) {
	    	    String[] evt = evenements.get(i);
	             String nomEvt = evt[0];
	             String nomImg = evt[1];
	             String descEvt = evt[2];
	             String dateEvt = evt[3];
	             
	             body.append("<div style='display: table; width: 100%; margin-bottom: 8px;'>")
	             .append("<div style='padding-right : 5px;'>"+(i+1)+"</div><p class='quote' style='display: table-cell; margin: 0;'>")
	             .append(nomEvt).append(" ").append(nomImg).append(" ").append(descEvt).append(" ").append(dateEvt)
	             .append("</p>")
	             .append("<a href='AdminController?action=").append(i).append("'>")
	             .append("<i class='fas fa-trash-alt' style='padding-left : 8px;'></i>")
	             .append("</a>")
	             .append("</div>");



	         
	     }
	     
	     body.append("  </div>\r\n")
		 	 .append("</div>\r\n");
		
	     
	     
	     
		String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
		
		PrintWriter out = response.getWriter();
		out.println(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}

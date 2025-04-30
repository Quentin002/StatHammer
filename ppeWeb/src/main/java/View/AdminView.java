package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Model.Evenement;


@WebServlet("/evenements")
@MultipartConfig
public class AdminView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminView() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("connexion");
        } 
		
		response.setContentType("text/html; charset=UTF-8");

		String titre = "StatHammer : Admin";
		
		String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;

		
	     //StringBuilder permet de travailler avec la méthode ".append" pour assembler chaine de caracatère par chaine de caractère 
	     StringBuilder body = new StringBuilder();
	     body.append("<div class='container'>\r\n")
		     .append("  <div class='left-panel'>\r\n")
		     .append("   <form method='post' action=\"AdminController\" enctype=\"multipart/form-data\">\r\n")
		     .append("       <label for='nom'>Nom :</label>\r\n")
		     .append("       <input type='text' name='nom' required/>\r\n")
		     .append("       <label for='desc'>Description :</label>\r\n")
		     .append("       <input type='text' name='desc' required/>\r\n")
		     .append("       <label for='date'>Date :</label>\r\n")
		     .append("       <input type='date' name='date' required/>\r\n")
		     .append("       <div class='custom-file'>\r\n")
		     .append("           <input type='file' id='imageUpload' name='image' accept='image/*' required />\r\n")
		     .append("           <label for='imageUpload'>Choisir une image</label>\r\n")
		     .append("       </div><br/>\r\n")
		     .append("       <input type='submit' value='Créer évènement'/>\r\n")
		     .append("   </form>\r\n")
		     .append("  </div>\r\n");
	     
	     body.append("  <div class='right-panel'>\r\n")
	         .append("  <table>");
	     
	     @SuppressWarnings("unchecked")
	     ArrayList<Evenement> evenements = (ArrayList<Evenement>) session.getAttribute("events");
	     
	     for (int i = 0; i < evenements.size(); i++) {
	    	 Evenement evt = evenements.get(i);
	    	 
	         String nomEvt = evt.getNom_evenement();
	         String nomImg = evt.getNom_image();
	         String descEvt = evt.getDescritption_evenement();
	         String dateEvt = evt.getData_evenement();
	         
	         System.out.println(nomEvt+nomImg+descEvt+dateEvt);
	         
	             
	     body.append("     <tr>")
	         .append("     <div style='display: table; width: 100%; margin-bottom: 8px;'>")
	         .append("        <td><div style='padding-right : 5px;'>"+"</div></td>")
	         .append("        <td><p class='quote' style='margin: 0;'>")
	         .append("            <b style='color:#42a5f5;'>"+nomEvt+"</b>").append(" ").append(nomImg).append(" ").append(descEvt).append(" ").append(dateEvt)
	         .append("        </p></td>")
	         .append("        <td><a class='' href='AdminController?supprEvt=").append(i).append("'>")
	         .append("           <i class='fas fa-trash-alt' style='padding-left : 8px;'></i>")
	         .append("        </a></td>")
	         .append("        <td><span class='image-preview'>")
	         .append("           <i class='fas fa-eye' style='padding-left: 10px;'></i>")
	         .append("           <img src='img/").append(nomImg).append("' class='thumbnail' />")
	         .append("        </span></td>")
	         .append("     </div>")
	         .append("     </tr>");
	         
	     }
	     
	     body.append("  </table>")
	         .append("  </div>\r\n")
		 	 .append("</div>\r\n")
		 	 .append("<script src='js/evtMontrerNom.js'></script>\n");
     
		String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
		
		PrintWriter out = response.getWriter();
		out.println(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

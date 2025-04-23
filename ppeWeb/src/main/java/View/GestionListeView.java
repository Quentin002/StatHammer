package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Model.ArmeeListe;

/**
 * Servlet implementation class GestionListeView
 */
@WebServlet("/GestionListeView")
public class GestionListeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionListeView() {
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
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ArrayList<Model.ArmeeListe> listes = (ArrayList<Model.ArmeeListe>) session.getAttribute("listes");
		
	             
		String titre = "StatHammer : Gestion liste";
	    String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
	    
	    
	    
	    StringBuilder listage=  new StringBuilder();

	    if (listes != null && !listes.isEmpty()) {
	        for (ArmeeListe liste : listes) {
	            int idliste = liste.getId();
	            String nomliste = liste.getNomListe();
	            String descrliste = liste.getDescriptionListe();
	            ArrayList<String> nomUniteliste = liste.getUniteListe();
	            int idArmeeliste = liste.getIdArmee();

	            listage.append("<div class='liste id='").append(idliste).append("'>\n")
	                   .append("<h2>").append(nomliste).append("</h2>\n")
	                   .append("<p>").append(descrliste).append("</p>\n");

	            if (nomUniteliste != null && !nomUniteliste.isEmpty()) {
	                listage.append("<ul>\n");
	                for (String nomUnite : nomUniteliste) {
	                    listage.append("<li>").append(nomUnite).append("</li>\n");
	                }
	                listage.append("</ul>\n");
	            } else {
	                listage.append("<p><em>Aucune unité dans cette liste.</em></p>\n");
	            }	
	            listage.append("<div class='GestionListe'>\n")
	            		.append("<button type='button' class='GestionListe_bouton'>Exporter\n")
	            		.append("</button>\n")
	            		.append("<form action='ModificationListeView' method='post' style='display:inline;'>\n")
	            		.append("<input type='hidden' name='idArmee' value='").append(idliste).append("' />\n")
	            		.append("<button type='submit' class='GestionListe_bouton'>Paramétrer</button>\n")
	            		.append("</form>\n")
	            		.append("<button type='button' class='GestionListe_bouton modal_ouverture' id='")
	            		.append(idliste) 
	            		.append("'>Supprimer</button>\n")
	            		.append("</div>\n")
	            		.append("</div>\n");
	            
	        }
	    }
	     	
	    String body= 
	    		"<h1>Interface de gestion des listes</h1>\n"
	    		+"<section class='GestionListe_structure'>\r\n"
	    		+"<div class='bouton_placement'>"
	    		+"<a href='AccueilView' class='bouton_gestionListe'>Retour</a>"
	    		+"<button type='button' class='bouton_gestionListe'>Importer une liste</button>"
	    		+"</div>"
	    		+"<div class='GestionListe_import'>"
	    		+ listage
	    		+"</div>"
	    		+"</section>"
	    		+"<div class='modal_overlay modal_hidden'>"
	    		+   "<div class='modal'>"
	    		+     "<p id='modal_message'>Voulez-vous vraiment supprimer cette armée ?</p>"
	    		+     "<div class='modal_actions'>"
	    		+       "<form method='POST' action='SupprimerListe'>"
	    		+         "<button type='submit' name='idliste' class='modal_btn modal_supprButton'>Supprimer</button>"
	    		+         "<button type='button' class='modal_btn modal_retourButton' >Annuler</button>"
	    		+       "</form>"
	    		+     "</div>"
	    		+   "</div>"
	    		+ "</div>";
	     
	    String scriptJs = "<script src=\"js/modalGestionListe.js\"></script>";
	   
	    String html = header + AccueilView.barDeNav + body + scriptJs + ConnexionView.footer;
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

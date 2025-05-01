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

import Controller.ConnexionController;
import Model.ArmeeListe;
import Model.Figurine;

/**
 * Servlet implementation class ModificationListeView
 */
@WebServlet("/ModificationListeView")
public class ModificationListeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificationListeView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("ConnexionView");
        }
        
        String titre = "StatHammer : Gestion liste";
	    String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
		
		String idArmeeParamStr = request.getParameter("idArmee");
		int idArmeeParam = Integer.parseInt(idArmeeParamStr);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ArrayList<Model.ArmeeListe> listes = (ArrayList<Model.ArmeeListe>) session.getAttribute("listes");
        System.out.println(listes);
        
        StringBuilder importListe=  new StringBuilder();

        if (listes != null && !listes.isEmpty()) {
            for (ArmeeListe liste : listes) {
                int idliste = liste.getId();
                String nomliste = liste.getNomListe();
                String descrliste = liste.getDescriptionListe();
                ArrayList<String> nomUniteliste = liste.getUniteListe();
                int idArmeeliste = liste.getIdArmee();

                if (idliste == idArmeeParam) {
                	importListe.append("<div>\n")
                           .append("<h2>").append(nomliste).append("</h2>\n")
                           .append("<p>").append(descrliste).append("</p>\n");

                    if (nomUniteliste != null && !nomUniteliste.isEmpty()) {
                    	importListe.append("<ul>\n");
                        for (String nomUnite : nomUniteliste) {
                        	importListe.append("<li>").append(nomUnite);
                        	ArrayList<Model.Figurine> figurines = ConnexionController.chargerFigurineListes(nomUnite, idliste);
                        	for (Model.Figurine figurine : figurines) {
                            	importListe.append("<ul>").append(figurine.getNom())
                            	.append("</ul>\n");
                            }
                        	importListe.append("</li>\n");
                        	importListe.append("<button onclick=").append("wawa").append(">Supprimer</button>");
                        }
                        importListe.append("</ul>\n");
                    } else {
                    	importListe.append("<p><em>Aucune unité dans cette liste.</em></p>\n");
                    }
                    importListe.append("</div>");
                }
            }
        }

        
        int idArmee= listes.get(0).getIdArmee();
        StringBuilder importModif = new StringBuilder();
        
        ArrayList<Model.ArmeeListe> unitModif = ConnexionController.chargerUnitModifListes(idArmeeParam, idArmee);
        session.setAttribute("Modiflistes", unitModif);
        importModif.append("<div>");
        for (ArmeeListe unit : unitModif) {
        	importModif.append("<ul>");
            for (String uniteName : unit.getUniteListe()) {
                importModif.append("<li>").append(uniteName);
                ArrayList<Model.Figurine> figurines = ConnexionController.chargerFigurineModifListes(uniteName, idArmee);
            	for (Model.Figurine figurine : figurines) {
                	importModif.append("<ul>").append(figurine.getNom())
                	.append("</ul>\n");
                }
            	importModif.append("</li>");
            }
            importModif.append("<button>Ajouter</button>");
            importModif.append("</ul>\n");
            		   
        }
        importModif.append("</div>");
        
        ArrayList<Model.ArmeeListe> modifListe = (ArrayList<Model.ArmeeListe>) session.getAttribute("Modiflistes");
        System.out.println(modifListe);
        
        StringBuilder testImportSession=  new StringBuilder();
        testImportSession.append("<div>");
        for (ArmeeListe unite : modifListe) {
        	testImportSession.append("<ul>");
            for (String uniteName : unite.getUniteListe()) {
            	testImportSession.append("<li>").append(uniteName);
               
            	testImportSession.append("</li>");
            }
            testImportSession.append("</ul>\n");
            		   
        }
        testImportSession.append("</div>");
        
        
        String body =
        		"<h1>Modificateur de liste</h1>\n"
        		+"<section class='ModificationListe_structure'>"
        		+importModif
        		+importListe
        		+testImportSession
        		+"</section>";
        
        String html = header + AccueilView.barDeNav+ body + ConnexionView.footer;
		out.println(html);
	}

}

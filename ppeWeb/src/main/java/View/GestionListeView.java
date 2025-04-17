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
		
		String titre = "StatHammer : Gestion compte";
	    String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;
		
	    String body= 
	    		"<h1>Interface de gestion des listes</h1>"
	    		+"<section class='GestionListe_structure'>\r\n"
	    		+"<div class='bouton_placement'>"
	    		+"<a href='AccueilView' class='bouton_gestionListe'>Retour</a>"
	    		+"<button type='button' class='bouton_gestionListe'>Importer une liste</button>"
	    		+"</div>"
	    		+"<div class='GestionListe_import'>"
	    		+"</div>"
	    		+"</section>";
	    
	    
	    String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
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

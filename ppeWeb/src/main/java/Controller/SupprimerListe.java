package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.ArmeeListe;
import View.AccueilView;
import View.ConnexionView;

/**
 * Servlet implementation class SupprimerListe
 */
@WebServlet("/SupprimerListe")
public class SupprimerListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerListe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("connexionFailed.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("connexion");
        } 
        int id = (int)session.getAttribute("id") ;
        
		String idListe = request.getParameter("idliste");
		System.out.println(idListe);
        
	        if (idListe != null && !idListe.isEmpty()) {
	            try {

	                ConnexionController.suppressionListe(idListe);
	                System.out.println("c'est suppr");

	                ArrayList<ArmeeListe> listes = (ArrayList<ArmeeListe>) session.getAttribute("listes");
	                if (listes != null) {
	                    listes.removeIf(liste -> Integer.toString(liste.getId()).equals(idListe));
	                    session.setAttribute("listes", listes); 
	                }
	                response.sendRedirect("gerer-liste");

	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	        } else {
	            response.sendRedirect("accueil");
	            System.out.println("bah non");
	        }
	}
	
}

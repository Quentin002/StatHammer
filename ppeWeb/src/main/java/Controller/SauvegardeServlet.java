package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.ArmeeListe;
import Model.Unit;
import Model.User;

/**
 * Servlet implementation class SauvegardeServlet
 */
@WebServlet("/SauvegardeServlet")
public class SauvegardeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SauvegardeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ArrayList<ArmeeListe> userList = (ArrayList<ArmeeListe>) session.getAttribute("listes");
		if(userList==null) {
			userList = new ArrayList<ArmeeListe>();
		}
		StockageCreerListe.getArmeeListe().setNom((String) session.getAttribute("nomListe"));
		String nom = (String) session.getAttribute("nom");
		int id = (int) session.getAttribute("id");
		String role = (String) session.getAttribute("role");
		Instanciation.insertListe(StockageCreerListe.getArmeeListe(), new User(nom,id,role));
		ArrayList<String> nomListe = new ArrayList<String>();
		for(Unit unit:StockageCreerListe.getArmeeListe().getUnits()) {
			nomListe.add(unit.getName());
		}
		StockageCreerListe.getArmeeListe().setUniteListe(nomListe);
		userList.add(StockageCreerListe.getArmeeListe());
		session.setAttribute("listes", userList);
		response.sendRedirect("accueil");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package vue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import controlleur.BDD;
import controlleur.StockageCreerListe;

/**
 * Servlet implementation class creerListeServlet
 */
@WebServlet("/CreerListeServlet")
public class CreerListeServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerListeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BDD.setInfos("400129","stathammer_greta_admin","stathammer_v1");
		
		StockageCreerListe.initFaction();
		StockageCreerListe.initArmee(StockageCreerListe.getNomFac().getFirst());
		StockageCreerListe.initUnit(StockageCreerListe.getNomArmee().getFirst());
		
		StockageCreerListe.initArmeeListe();
		
		
		
		
		response.sendRedirect("creerListe.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

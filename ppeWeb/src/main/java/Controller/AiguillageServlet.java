package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class AiguillageServlet
 */
@WebServlet("/AiguillageServlet")
public class AiguillageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AiguillageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String plus = request.getParameter("plus");
		String moins = request.getParameter("moins");
		String faction = request.getParameter("faction");
		String armee = request.getParameter("armee");
		
		String destination = "creerListe.jsp";
		
		if(plus!=null) {
			
			if(!StockageCreerListe.getArmeeListe().getUnits().contains(StockageCreerListe.getUnit(plus))) {
				
				StockageCreerListe.getUnit(plus).setFigurine(Instanciation.getFigurine2(plus)) ;
				StockageCreerListe.getArmeeListe().addUnit(StockageCreerListe.getUnit(plus));
				
			}
			
			
		}if (moins != null) {
			StockageCreerListe.getArmeeListe().removeUnit(StockageCreerListe.getUnit(request.getParameter("moins")));
			
		}
		if(faction!=null) {
			StockageCreerListe.initArmeeListe();
			StockageCreerListe.initArmee(faction);
			//StockageCreerListe.initUnit(StockageCreerListe.getNomArmee().getFirst());
			StockageCreerListe.initUnit(StockageCreerListe.getNomArmee().get(0));
		}
		if(armee!=null) {
			StockageCreerListe.initArmeeListe();
			StockageCreerListe.initUnit(armee);
		}
		if(request.getParameter("creer")!=null && !request.getParameter("nomListe").equals("") && StockageCreerListe.getArmeeListe().getUnits().size()!=0) {
			//Instanciation.insertListe(StockageCreerListe.getArmeeListe(), null);
			HttpSession session = request.getSession();
			session.setAttribute("nomListe", request.getParameter("nomListe"));
			destination = "SauvegardeServlet";
		}
		response.sendRedirect(destination);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

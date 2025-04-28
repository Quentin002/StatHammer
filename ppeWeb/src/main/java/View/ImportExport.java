package View;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Model.Evenement;

/**
 * Servlet implementation class ImportExport
 */
@WebServlet("/ImportExport")
@MultipartConfig
public class ImportExport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportExport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session=request.getSession(false);
        if (session==null) {
            response.sendRedirect("connexion");
        } 
		
		response.setContentType("text/html; charset=UTF-8");

		String titre = "StatHammer : Page de test";
		
		String header = ConnexionView.headerTop + titre + ConnexionView.headerBottom;

		
	     //StringBuilder permet de travailler avec la méthode ".append" pour assembler chaine de caracatère par chaine de caractère 
	     StringBuilder body = new StringBuilder();
	     body.append("<form action='ImportExportController' method='get'><input type=text name=export><input type=submit value='Exporter'></form><form action='ImportExportController' method='post' enctype=\"multipart/form-data\"><input type='file' id='listUpload' name='liste' accept='.txt'><input type=submit value='Importer'></form>");
     
		String html = header + AccueilView.barDeNav + body + ConnexionView.footer;
		
		PrintWriter out = response.getWriter();
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

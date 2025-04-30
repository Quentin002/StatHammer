package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import Controller.ConnexionController;
import Model.ArmeeListe;
import Model.Evenement;
import Model.Figurine;
import Model.Unit;
import Model.User;

/**
 * Servlet implementation class ImportExportController
 */
@WebServlet("/ImportExportController")
@MultipartConfig
public class ImportExportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nomFichier;
	private String texteFichier;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportExportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("connexion");
            return;
        }

        ArrayList<Model.ArmeeListe> listes = (ArrayList<Model.ArmeeListe>) session.getAttribute("listes");
        String strNumber = request.getParameter("export");

        if (strNumber == null || listes == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres manquants.");
            return;
        }
        else {
        	
            int number = Integer.parseInt(strNumber);
            ArmeeListe liste = listes.get(number);
            System.out.println(liste);

            String nomListe = liste.getNomListe();
            String descListe = liste.getDescriptionListe();

            StringBuilder contenu = new StringBuilder();
            contenu.append(nomListe).append("\n");
            contenu.append(descListe).append("\n");
            
            
            ArrayList<String> nomsUnites = liste.getUniteListe();
            for (String nomUnite : nomsUnites) {
            	
                contenu.append(nomUnite).append("\n");
                
            }

            // Prépare la réponse pour téléchargement
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nomListe + ".txt\"");

            
            response.getWriter().write(contenu.toString());
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("connexion");
            return;
        }
		
        //Réupération du fichier avec Part
		Part listPart = request.getPart("liste");
        InputStream ipListe = listPart.getInputStream();
        
        String fileName = Paths.get(listPart.getSubmittedFileName()).getFileName().toString();
        System.out.println(fileName);
        
        String strListe = listPart.toString();
        System.out.println(strListe);
        
        BufferedReader buff = new BufferedReader(new InputStreamReader(ipListe));
        
        //Permet de récupérer les lignes pour en faire une String liées par "&"
        String result = buff.lines().collect(Collectors.joining("&"));
        System.out.println(result);
        
        String login = (String) session.getAttribute("nom");
        String mdp = (String) session.getAttribute("mdp");
        int id = (int) session.getAttribute("id");
        String role = (String) session.getAttribute("role");
        String email = (String) session.getAttribute("email");
        User user = new User(login, id, role);
        
        
        ArrayList<String> rslts = new ArrayList<String>(Arrays.asList(result.split("&")));

        
        /*
        
        ArrayList<String> newRslts = new ArrayList<String>();
        int i = 0;
        for (String rslt : rslts) {
        	if (i > 1) {
        		newRslts.add(rslt);
        		System.out.println(rslt);
        	}
        	i++;
        }
        

        String[] rsltsTab = newRslts.toArray(new String[0]);
        
        for (String newRslt : rsltsTab) {
    		System.out.println(newRslt);
        }
        */
        ArrayList<String> listeUnites = new ArrayList<String>(rslts.subList(2, rslts.size()));
        ArmeeListe armee = Instanciation.importListe(rslts.get(0), rslts.get(1), listeUnites, user);

        ArrayList<ArmeeListe> userList = (ArrayList<ArmeeListe>) session.getAttribute("listes");
        userList.add(armee);
        session.setAttribute("listes", userList);
        
        response.sendRedirect("gerer-liste");
        
	}


	public void Fichier (String nvNomFichier) {
		this.nomFichier = nvNomFichier+".txt";
	}
	
	public void nvFichier() {
	    try {

	          File monFichier = new File(nomFichier);
	          System.out.println("Chemin absolu : " + monFichier.getAbsolutePath());

	          if (monFichier.createNewFile()) {
	          System.out.println("Création du fichier '" + monFichier.getName()+"'");
	          } else {
	          System.out.println("Le fichier existe déjà.");
	          }
	    	} catch (IOException e) {
	        System.out.println("Une erreur s'est produite.");
	        e.printStackTrace();
	      }
	}
	
	public void chgTexte(String nvTexteFichier) {
		this.texteFichier = nvTexteFichier;
		try {
		        FileWriter monTexte = new FileWriter(nomFichier);
		        monTexte.write(texteFichier);
		        monTexte.close();
		        System.out.println("Écriture dans le fichier réussie.");
		      } catch (IOException e) {
		        System.out.println("Une erreur s'est produite.");
		        e.printStackTrace();
		      }  
	}   
	
	public void lire() {
	    try {
	        File monFichier = new File(nomFichier);
	        Scanner maLecture = new Scanner(monFichier);
	        while (maLecture.hasNextLine()) {
	          String data = maLecture.nextLine();
	          System.out.println(data);
	        }
	        maLecture.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("Une erreur s'est produite.");
	        e.printStackTrace();
	      }		
	}
	
	public void effacer() {
	    File monFichier = new File(nomFichier); 
	    if (monFichier.delete()) { 
	      System.out.println("Suppression du fichier '" + monFichier.getName()+"'");
	    } else {
	      System.out.println("Échec de la suppression du fichier.");
	    } 
	}
	
}

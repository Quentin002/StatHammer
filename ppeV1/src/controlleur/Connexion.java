package controlleur;

public class Connexion {
	public static Boolean verif(String login, String mdp,BDD conec) {
		
		
		Bool test = new Bool(false);
		conec = new BDD(login,mdp,"ppe1_v1",test);
		if (test.getBin()) {
			conec.close();
		}
		
		return test.getBin();
	}
}

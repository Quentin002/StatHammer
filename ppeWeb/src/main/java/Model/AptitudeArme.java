package Model;

public class AptitudeArme {

	private String nomArme;
	private int val;
	
	public AptitudeArme(String nomArme, int val) {
		this.nomArme = nomArme;
		this.val = val;
	}
	public AptitudeArme(String nomArme) {
		this.nomArme = nomArme;
	}

	public void ArmeAptitude() {

	}
	public String getNomArme() {
		return nomArme;
	}
	public int getVal() {
		return val;
	}

}

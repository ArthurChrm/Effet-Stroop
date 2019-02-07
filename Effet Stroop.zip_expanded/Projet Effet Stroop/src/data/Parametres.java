package data;

public class Parametres {

	
	private int largeur = 0;
	private int hauteur = 0;
	private String police = null;
	private int pourcentage = 0;
	
	public Parametres(int hauteur, int largeur, String police, int pourcentage){
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.police = police;
		this.pourcentage = pourcentage;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	
}

package data;

public class Couleur {

	private String nom;
	private double R;
	private double V;
	private double B;

	public Couleur(String nom, double R, double V, double B) {
		this.nom = nom;
		this.R = R;
		this.V = V;
		this.B = B;
	}

	public String getNom() {
		return nom;
	}

	public double getR() {
		return R;
	}

	public double getV() {
		return V;
	}

	public double getB() {
		return B;
	}

	public String getRVB() {
		return (int) (R * 255) + "," + (int) (V * 255) + "," + (int) (B * 255);
	}

	@Override
	public String toString() {
		return nom;
	}

}

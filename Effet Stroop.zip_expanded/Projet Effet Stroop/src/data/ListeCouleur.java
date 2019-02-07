package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ListeCouleur {

	private static List<Couleur> listeCouleur = new ArrayList<Couleur>();

	public static void ajouterUneCouleur(Couleur couleur) {
		listeCouleur.add(couleur);
	}

	public static void enregistrerDansFichier() throws IOException {
		// Cette méthode va générer un fichier .CSV contenant toutes les
		// couleurs enrgistrés dans listeCouleur
		FileWriter fw = new FileWriter("couleurs.csv");
		for (Couleur current : listeCouleur) {
			fw.append(current.getNom() + ";");
			fw.append(current.getR() + ";");
			fw.append(current.getV() + ";");
			fw.append(current.getB() + ";\n");
		}
		fw.close();
	}

	public static void chargerListeDepuisUnFichier() throws IOException {
		// Cette méthode va remplacer la liste actuelle par une liste qui sera
		// chargé via un fichier .CSV, si il n'éxiste pas il sera créé avec
		// quelques couleur

		viderLaListe();
		File testLecture = new File("couleurs.csv");
		if (testLecture.exists()) {
			BufferedReader lecture = new BufferedReader(new FileReader("couleurs.csv"));
			String line = "";
			while ((line = lecture.readLine()) != null) {
				String[] data = line.split(";");
				ajouterUneCouleur(new Couleur(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]),
						Double.parseDouble(data[3])));
			}
			lecture.close();
		} else {
			testLecture.createNewFile();
			FileWriter fw = new FileWriter("couleurs.csv");
			fw.append("Rouge;");
			fw.append("1.0;");
			fw.append("0.0;");
			fw.append("0.0;\n");

			fw.append("Bleu;");
			fw.append("0.0;");
			fw.append("0.0;");
			fw.append("1.0;\n");

			fw.append("Vert;");
			fw.append("0.0;");
			fw.append("1.0;");
			fw.append("0.0;\n");
			fw.close();
			chargerListeDepuisUnFichier();
		}
	}

	public static Couleur couleurAleatoire() {
		Random random = new Random();
		int randomNum = random.nextInt((listeCouleur.size() - 1 - 0) + 1) + 0;
		return listeCouleur.get(randomNum);
	}

	public static String rbgAleatoire() {
		Random random = new Random();
		int randomNum = random.nextInt((listeCouleur.size() - 1 - 0) + 1) + 0;
		return listeCouleur.get(randomNum).getRVB();
	}

	public static void viderLaListe() {
		listeCouleur.clear();
	}

	public static List<Couleur> getListeCouleur() {
		return listeCouleur;
	}

	public static void supprimerUneCouleur(Couleur couleur) {
		if (listeCouleur.contains(couleur)) {
			listeCouleur.remove(couleur);
		}
	}

	public static String affichage() {
		String rep = "";
		for (Couleur current : listeCouleur) {
			rep += current + "\n";
		}
		return rep;
	}

}

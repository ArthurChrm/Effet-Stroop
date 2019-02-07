package application.jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import application.Main;
import application.parametres.ParametresController;
import data.Couleur;
import data.ListeCouleur;
import data.Score;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JeuController {

	@FXML
	private GridPane grille;

	private ArrayList<Button> listeBoutons = new ArrayList<Button>();

	private ArrayList<Button> intrus = new ArrayList<Button>();
	private BorderPane stage_parent;
	
	@FXML
	private Text textIntrusTrouve;
	
	@FXML
	private Text textIntrusTotal;

	public static Score score = new Score();

	public void initialize() {
		redimensionnerGrille(Main.donnees.getLargeur(), Main.donnees.getHauteur());

		// On va maintenant définir le style et l'action de chaque bouton
		// On commence par faire en sorte que tout les textes correspondent à la
		// bonne couleur
		for (Button current : listeBoutons) {
			Couleur couleur = ListeCouleur.couleurAleatoire();
			current.setText(couleur.getNom());
			current.setStyle("-fx-background-color:black; -fx-font: 30 system; -fx-text-fill: rgb(" + couleur.getRVB()
					+ ");-fx-font-family: \"" + Main.donnees.getPolice() + "\"");
			current.setPrefWidth(150);
			current.setPrefHeight(25);
			current.setCursor(Cursor.HAND);
			current.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					clicBoutonPasIntru(e);
				}
			});

		}
		// On va modifier certains boutons pour les rendre anormaux
		do {
			Random random = new Random();
			int randomNum = random.nextInt(listeBoutons.size() - 1 - 0) + 0;
			Couleur rgbAleat;
			if (!intrus.contains(listeBoutons.get(randomNum))) {
				intrus.add(listeBoutons.get(randomNum));
				do {
					// on va chercher une couleur qui ne correspond pas au texte
					// du bouton
					rgbAleat = ListeCouleur.couleurAleatoire();
				} while (rgbAleat.getNom() == listeBoutons.get(randomNum).getText());

				listeBoutons.get(randomNum).setStyle(
						"-fx-background-color:black; -fx-font: 30 system; -fx-text-fill: rgb("
								+ rgbAleat.getRVB() + "); -fx-font-family: \"" + Main.donnees.getPolice()
								+ "\"");
				listeBoutons.get(randomNum).setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						clicBoutonIntrus(e);
					}
				});
			}
		} while (intrus.size() - 1 < (listeBoutons.size() * (Main.donnees.getPourcentage())) / 100);
		textIntrusTotal.setText(""+intrus.size());
		textIntrusTrouve.setText("0");
	}

	public void clicBoutonPasIntru(ActionEvent e) {
		Node n = (Node) e.getSource();
		n.setStyle(" -fx-background-color: red; -fx-font: 30 system; -fx-font-family: \"" + Main.donnees.getPolice()
				+ "\"");
		score.setMauvaiseReponse(score.getMauvaiseReponse() + 1);
		;
	}

	public void clicBoutonIntrus(ActionEvent e) {
		Node n = (Node) e.getSource();
		if (intrus.contains(n)) {
			n.setStyle(" -fx-background-color: green; -fx-font: 30 system; -fx-font-family: \""
					+ Main.donnees.getPolice() + "\"");
			score.setBonneReponse(score.getBonneReponse() + 1);
			intrus.remove(n);
			textIntrusTrouve.setText(""+score.getBonneReponse());
			if (intrus.isEmpty()) {
				// Si tout les intrus ont étés trouvés on change
				// d'ecran
				try {
					stage_parent = (BorderPane) FXMLLoader
							.load(getClass().getResource("../finDePartie/InterfaceFinPartie.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Scene sceneRecords = new Scene(stage_parent, 600, 600);

				Stage stagePrincipal = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stagePrincipal.setScene(sceneRecords);
				stagePrincipal.centerOnScreen();
				stagePrincipal.show();
			}
		}

	}

	public void redimensionnerGrille(double largeur, double hauteur) {

		grille.setGridLinesVisible(false);

		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setHalignment(HPos.CENTER);

		RowConstraints rowConstraints = new RowConstraints();

		grille.getColumnConstraints().clear();
		grille.getRowConstraints().clear();

		// on va créer le bon nombre de colonne dans la gridPane
		for (int i = 0; i < largeur; i++) {
			columnConstraints.setPercentWidth(100 - largeur);
			grille.getColumnConstraints().add(columnConstraints);
		}

		// on va créer le bon nombre de ligne dans le gridPane
		for (int i = 0; i < hauteur; i++) {
			rowConstraints.setPercentHeight(100 - hauteur);
			grille.getRowConstraints().add(rowConstraints);
		}

		// On ajoute les boutons vierge à la grille
		Button btnTemp;
		for (double i = 0; i < largeur; i++) {
			for (double j = 0; j < hauteur; j++) {
				btnTemp = new Button("Text");
				listeBoutons.add(btnTemp);
				grille.add((Node) listeBoutons.get(listeBoutons.lastIndexOf(btnTemp)), (int) i, (int) j);
			}
		}

	}
}

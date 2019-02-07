package application.accueil;

import java.io.IOException;

import data.ListeCouleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AccueilController {

	@FXML
	private Button jouer;

	@FXML
	private Button parametres;

	private BorderPane stage_jeu;

	private BorderPane stage_parent;

	public void initialize() throws IOException {
		ListeCouleur.chargerListeDepuisUnFichier();
	}

	@FXML
	public void clicJouer(ActionEvent event) throws IOException {
		// Cette méthode va charger la scene correspondant aux paramètres (c'est
		// à dire le nombre de bouttons voulu)

		stage_jeu = (BorderPane) FXMLLoader.load(getClass().getResource("../jeu/InterfaceJeu.fxml"));
		Scene sceneRecords = new Scene(stage_jeu, 1280, 720);

		Stage stagePrincipal = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stagePrincipal.setScene(sceneRecords);
		stagePrincipal.centerOnScreen();
		stagePrincipal.setMaximized(true);
		stagePrincipal.show();
	}

	@FXML
	public void clicParametres(ActionEvent event) throws IOException {
		// Cette méthode va charger la scene permettant d'acceder aux parametres
		// de l'applciation

		stage_parent = (BorderPane) FXMLLoader.load(getClass().getResource("../parametres/InterfaceParametres.fxml"));
		Scene sceneRecords = new Scene(stage_parent, 800, 900);

		Stage stage_parametres = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage_parametres.setScene(sceneRecords);
		stage_parametres.centerOnScreen();
		stage_parametres.show();
	}

}

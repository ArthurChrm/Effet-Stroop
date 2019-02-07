package application.finDePartie;

import java.io.IOException;

import application.jeu.JeuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class finPartieController {

	@FXML
	private Button rejouer;
	
	@FXML
	private Button quitter;
	
	@FXML
	private Text scoreText;
	
	private BorderPane stage_parent;
	
	
	public void initialize(){
		scoreText.setText("Vous avez "+JeuController.score.getMauvaiseReponse()+" mauvaise(s) réponse(e) contre "+JeuController.score.getBonneReponse()+" bonne(s) réponse(s)");
		JeuController.score.setBonneReponse(0);
		JeuController.score.setMauvaiseReponse(0);
	}
	
	@FXML
	public void clicRejouer(ActionEvent e) throws IOException{
	
		stage_parent = (BorderPane) FXMLLoader.load(getClass().getResource("../accueil/InterfaceAccueil.fxml"));

		Scene sceneRecords = new Scene(stage_parent, 600, 600);

		Stage stagePrincipal = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stagePrincipal.setScene(sceneRecords);
		stagePrincipal.centerOnScreen();
		stagePrincipal.show();
	}
	
	@FXML
	public void clicQuitter(){
		System.exit(0);
	}
	
}

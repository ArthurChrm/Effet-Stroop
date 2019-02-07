package application;


import java.io.IOException;

import data.Parametres;
import data.ListeCouleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	public static Parametres donnees = new Parametres(4,4,"",40);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// On charge l'écran d'accueil
			FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("accueil/interfaceAccueil.fxml"));
			BorderPane root = rootLoader.load();

			// On affiche l'écran d'accueil
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		ListeCouleur.chargerListeDepuisUnFichier();		
		launch(args);
	}
}

package application.parametres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import application.Main;
import data.Couleur;
import data.Parametres;
import data.ListeCouleur;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ParametresController {

	@FXML
	private ChoiceBox<String> choixDimLargeur;

	@FXML
	private ChoiceBox<String> choixDimHauteur;

	@FXML
	private ChoiceBox<String> choixPolice;

	@FXML
	private ChoiceBox<String> choixPourcentage;

	@FXML
	private TextField nomCouleur;

	@FXML
	private ColorPicker choixCouleur;

	@FXML
	private Button ajouterElement;

	@FXML
	private Button valider;

	@FXML
	private Button retour;

	@FXML
	private ListView<Couleur> listeCouleur;

	@FXML
	private Button supprimer;

	private BorderPane stage_parent;

	@FXML
	private ScrollPane sc;

	// Les deux lignes suivantes vont récupérer les polices du système
	List<String> familiesList = Font.getFamilies();
	ObservableList<String> familiesObservableList = FXCollections.observableArrayList(familiesList);

	ArrayList<String> pourcentage = new ArrayList<String>();

	public void initialize() {

		choixDimLargeur.setStyle("-fx-background-color: black; -fx-border-color: white;");
		choixDimHauteur.setStyle("-fx-background-color: black; -fx-border-color: white;");
		for (int i = 3; i <= 6; i++) {
			choixDimLargeur.getItems().add("" + i);
			choixDimHauteur.getItems().add("" + i);
		}
		choixDimLargeur.getSelectionModel().selectFirst();
		choixDimHauteur.getSelectionModel().selectFirst();

		// On va ajouter les polices récupérées précedemment à choixPolice
		choixPolice.getItems().addAll(familiesObservableList);

		// On va maintenant afficher les différentes couleur dans la
		// listeCouleur
		ObservableList<Couleur> couleurObservableList = FXCollections
				.observableArrayList(ListeCouleur.getListeCouleur());
		listeCouleur.getItems().clear();
		listeCouleur.getItems().addAll(couleurObservableList);

		// On va ajouter les pourcentages dans choixPourcentage
		for (int i = 10; i <= 90; i += 10) {
			pourcentage.add("" + i);
		}
		ObservableList<String> pourcentageObservableList = FXCollections.observableArrayList(pourcentage);
		choixPourcentage.getItems().addAll(pourcentageObservableList);
		choixPourcentage.getSelectionModel().select(3);
	}

	public void clicValider(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Enregistrement des paramètres");
		alert.setHeaderText("Voulez-vous sauvegarder vos changements ?");
		alert.setContentText("Cliquez sur une des propositions suivantes :");

		ButtonType boutonOui = new ButtonType("Oui");
		ButtonType boutonNon = new ButtonType("Non");
		ButtonType boutonAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(boutonOui, boutonNon, boutonAnnuler);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == boutonOui) {
			Main.donnees.setHauteur(Integer.parseInt(choixDimHauteur.getValue()));
			Main.donnees.setLargeur(Integer.parseInt(choixDimHauteur.getValue()));
			Main.donnees.setPolice(choixPolice.getValue());
			Main.donnees.setPourcentage(Integer.parseInt(choixPourcentage.getValue()));
			ListeCouleur.enregistrerDansFichier();
			alert.close();

			retourAccueil(event);

		} else if (result.get() == boutonNon) {
			retourAccueil(event);
			alert.close();
		} else {
			alert.close();
		}

	}

	public void clicAjouterElement() {
		if (nomCouleur.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Saisie incorrecte");
			alert.setHeaderText("La saisie de votre nom de couleur est invalide !");
			alert.setContentText("Vous devez renseigner un nom de couleur avant de valider.");

			alert.showAndWait();
		} else {
			Color couleur = choixCouleur.getValue();
			ListeCouleur.ajouterUneCouleur(
					new Couleur(nomCouleur.getText(), couleur.getRed(), couleur.getGreen(), couleur.getBlue()));
			ObservableList<Couleur> couleurObservableList = FXCollections
					.observableArrayList(ListeCouleur.getListeCouleur());
			listeCouleur.getItems().clear();
			listeCouleur.getItems().addAll(couleurObservableList);
		}
		System.out.println(ListeCouleur.affichage());
	}

	@FXML
	private void clicSupprimer() {
		ListeCouleur.supprimerUneCouleur(listeCouleur.getSelectionModel().getSelectedItem());
		ObservableList<Couleur> couleurObservableList = FXCollections
				.observableArrayList(ListeCouleur.getListeCouleur());
		listeCouleur.getItems().clear();
		listeCouleur.getItems().addAll(couleurObservableList);
	}

	@FXML
	private void retourAccueil(ActionEvent event) throws IOException {
		listeCouleur.getItems().clear();
		stage_parent = (BorderPane) FXMLLoader.load(getClass().getResource("../accueil/InterfaceAccueil.fxml"));
		Scene sceneRecords = new Scene(stage_parent, 600, 600);

		Stage stagePrincipal = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stagePrincipal.setScene(sceneRecords);
		stagePrincipal.centerOnScreen();
		stagePrincipal.show();
	}

}

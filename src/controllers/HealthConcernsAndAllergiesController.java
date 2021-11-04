/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Health Concerns and Allergies UI
 */

/* ToDo - Should load patient's immunization record and past visits */

package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HealthConcernsAndAllergiesController {

	@FXML
	private TextArea healthConcerns;

	@FXML
	private TextArea allergies;

	public void AddHealthConcernsAndAllergies(ActionEvent event) {
		/* ToDo - Should be removed before marking story as done */
		System.out.println("patient's health concerns and allergies successfully added");
		try {
			/* ToDo - @Nipoon is working on story related to adding health concern and allergies in the system */
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pages/SearchPatient.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
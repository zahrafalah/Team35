/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Search Patient UI
 */

package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Patient;

public class SearchPatientController {
	private Patient patient;
	
	@FXML
	private MenuButton username;
	
	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private DatePicker dob;
	
	public void SetPatient(Patient patient) {
		this.patient = patient;
		username.setText(patient.getUsername());
	}

	
	public void SearchPatient(ActionEvent event) {
		/* ToDo - Should be removed before marking story as done */
		System.out.println("patient successfully searched");
		try {
			/* ToDo - should search patient in system and redirect to doctor's dashboard or add vitals page for doctor and nurse users */
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
					
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pages/AddVitals.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void startMessaging(ActionEvent event){
		System.out.println("Messaging");
		try {
			
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/messaging.fxml"));
			Parent root = (Parent)loader.load();
			MessagingController messagingController = (MessagingController) loader.getController();
			messagingController.setPatient(patient);
			System.out.println(patient.getUsername());
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
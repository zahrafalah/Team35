/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Search Patient UI
 */

package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Doctor;
import models.Nurse;
import models.Patient;

public class SearchPatientController {
	
	private Patient patient;
	
	@FXML
	private Label username;
	
	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private DatePicker dob;

	private Nurse nurse;

	private Doctor doctor;
	
	public void Logout(ActionEvent event) {
		LogoutController logout = new LogoutController();
		logout.Logout(event);
	}
	
	public void SetPatient(Patient patient) {
		this.patient = patient;
		username.setText(patient.getUsername());
	}
	public void SetNurse(Nurse nurse) {
		this.nurse = nurse;
		username.setText(nurse.getUsername());
	}
	public void SetDoctor(Doctor doctor) {
		this.doctor = doctor;
		username.setText(doctor.getUsername());
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

	
}
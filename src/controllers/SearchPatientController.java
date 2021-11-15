/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Search Patient UI
 */

package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Doctor;
import models.Nurse;
import models.Patient;
import database.Driver;
import database.PatientQuery;

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
	
	@FXML
	private Label errorMessage;

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
	
	private boolean isChildPatient(String dob) {
		LocalDate today = LocalDate.now();                          //Today's date
		LocalDate birthday = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Period p = Period.between(birthday, today);
		return p.getYears() <= 12;
	}
	
	private Patient searchPatient(Connection conn) throws Exception {
		PatientQuery patientQuery = new PatientQuery(conn);
		Patient patient = patientQuery.searchPatient(this.firstName.getText(), this.lastName.getText(), this.dob.getValue().toString());
		return patient;
	}
	
	public void SearchPatient(ActionEvent event) throws Exception {
		try {
			Connection conn = Driver.getConnection();
			patient = searchPatient(conn);
			if (patient == null) {
				this.errorMessage.setText("Patient not found. Try again!");
				return;
			}
			System.out.println("patient found with id: " + patient.getUsername());
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			if(stage.getUserData().getClass().getName().equals("models.Nurse")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/AddVitals.fxml"));
				Parent root = loader.load();
				AddVitalsController controller = (AddVitalsController) loader.getController();
				controller.setPatient(patient);
		        controller.setNurse(nurse);
		        controller.setIsChildPatient(isChildPatient(patient.getDob()));
		        Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
			} else {
				// ToDo: Kartavya to redirect the user to doctor's dashboard page
				System.out.println("logged in as doctor");
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	
}
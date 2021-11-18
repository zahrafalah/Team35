/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Add Vitals UI
 */

/* ToDo - Should update UI as per patient's age */

package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import database.Driver;
import database.PatientQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Nurse;
import models.Patient;

public class AddVitalsController {

	@FXML
	private Label username;
	
	@FXML
	private TextField weight;

	@FXML
	private TextField height;

	@FXML
	private TextField temperature;

	@FXML
	private TextField bpHigh;

	@FXML
	private TextField bpLow;

	@FXML
	private Label errorMessage;
	
	private Patient patient;
	
	private Nurse nurse;
	
	private boolean disableBP;
	
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
		username.setText(this.nurse.getUsername());
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setIsChildPatient(boolean isChildPatient) {
		this.disableBP = isChildPatient;
		this.bpHigh.setDisable(this.disableBP);
		this.bpLow.setDisable(this.disableBP);
	}
	
	public void Logout(ActionEvent event) {
		LogoutController logout = new LogoutController();
		logout.Logout(event);
	}
	
	private String saveVitals(Connection conn) throws Exception {
		PatientQuery patientQuery = new PatientQuery(conn);
		String result = patientQuery.saveVitals(this.weight.getText(), this.height.getText(),
				this.temperature.getText(), this.bpHigh.getText(), this.bpLow.getText(), this.disableBP);
		return result;
	}

	private String createVisit(Connection conn, String patientId, String vitalId) throws Exception {
		PatientQuery patientQuery = new PatientQuery(conn);
		String result = patientQuery.createVisit(patientId, vitalId);
		return result;
	}

	public void AddVitals(ActionEvent event) throws Exception {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Connection conn = Driver.getConnection();
			String vitalId = saveVitals(conn);
			if (vitalId == null) {
				this.errorMessage.setText("Coundn't add vitals. Try again!");
				return;
			}
			
			String visitId = createVisit(conn, Integer.toString(patient.getId()), vitalId);
			if (visitId != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/HealthConcernsAndAllergies.fxml"));
				Parent root = loader.load();
				HealthConcernsAndAllergiesController controller = (HealthConcernsAndAllergiesController) loader.getController();
				controller.setPatient(patient);
				controller.setNurse(this.nurse);
				controller.setVisitId(visitId);
				Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
				return;
			}
			this.errorMessage.setText("Coundn't create patient's visit record. Try again!");
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
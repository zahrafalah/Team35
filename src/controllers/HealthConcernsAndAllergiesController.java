/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Health Concerns and Allergies UI
 */

/* ToDo - Should load patient's immunization record and past visits */

package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import database.Driver;
import database.PatientQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Nurse;
import models.Patient;

public class HealthConcernsAndAllergiesController {

	@FXML
	private TextArea healthConcerns;

	@FXML
	private TextArea allergies;

	@FXML
	private Label username;
	
	@FXML
    private VBox container;
	
	private Patient patient;
	
	private Nurse nurse;
	
	private String visitId;
	
	public void Logout(ActionEvent event) {
		LogoutController logout = new LogoutController();
		logout.Logout(event);
	}
	
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
		username.setText(this.nurse.getUsername());
	}
	
	public void setPatient(Patient patient) throws SQLException {
		this.patient = patient;
		Connection conn = Driver.getConnection();
		String immunization = getPatientImmunizations(conn, this.patient.getId());
		String [] immunizations = immunization.split(",");
		for(int i=0; i< immunizations.length; i++) {
			Label label = new Label(immunizations[i]);
			label.setPadding(new Insets(4));
            container.getChildren().add(label);
		}
	}
	
	private String getPatientImmunizations(Connection conn, int patientId) throws SQLException {
		PatientQuery patientQuery = new PatientQuery(conn);
		String immunization = patientQuery.getPatientImmunizations(patientId);
		return immunization;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	
	public void initialize() {
//		System.out.println("patient id: " + this.patient.getId());
	}

	public void AddHealthConcernsAndAllergies(ActionEvent event) {
		/* ToDo - Should be removed before marking story as done */
		System.out.println("patient's health concerns and allergies successfully added");
		try {
			/* ToDo - @Nipoon is working on story related to adding health concern and allergies in the system */
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchPatient.fxml"));
	        Parent root = (Parent) loader.load();
	        SearchPatientController controller = (SearchPatientController) loader.getController();
	        controller.SetNurse(this.nurse);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
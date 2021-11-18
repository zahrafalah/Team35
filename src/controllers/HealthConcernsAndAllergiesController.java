/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Health Concerns and Allergies UI
 */

/* ToDo - Should load patient's immunization record and past visits */

package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.Driver;
import database.PatientQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Nurse;
import models.Patient;
import models.VisitRecord;

public class HealthConcernsAndAllergiesController {

	@FXML
	private TextArea healthConcerns;

	@FXML
	private TextArea allergies;

	@FXML
	private Label username;

	@FXML
	private VBox container;

	@FXML
	private VBox vBox;

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
		String[] immunizations = immunization.split(",");
		for (int i = 0; i < immunizations.length; i++) {
			Label label = new Label(immunizations[i]);
			label.setPadding(new Insets(4));
			container.getChildren().add(label);
		}
		List<VisitRecord> visitRecords = getPatientVisitsRecords(conn, this.patient.getId());
		Accordion accordion = new Accordion();
		for (int i = 0; i < visitRecords.size(); i++) {
			if (visitRecords.get(i).getHealthIssue().length() > 0) {
				Label label = new Label();
				label.setText("Health issues - " + visitRecords.get(i).getHealthIssue() + "\nPrescription - "
						+ visitRecords.get(i).getPrescription());
				label.setWrapText(true);
				label.setPadding(new Insets(4));
				TitledPane pane1 = new TitledPane(visitRecords.get(i).getCreatedAt(), label);
				accordion.getPanes().add(pane1);
			}
		}
		this.vBox.getChildren().add(accordion);
	}

	private String getPatientImmunizations(Connection conn, int patientId) throws SQLException {
		PatientQuery patientQuery = new PatientQuery(conn);
		List<String> patientData = patientQuery.getPatientData(patientId);
		this.healthConcerns.setText(patientData.get(1));
		this.allergies.setText(patientData.get(2));
		return patientData.get(0);
	}

	private List<VisitRecord> getPatientVisitsRecords(Connection conn, int patientId) throws SQLException {
		PatientQuery patientQuery = new PatientQuery(conn);
		List<VisitRecord> visitRecords = patientQuery.getPatientVisitsRecords(patientId);
		return visitRecords;
	}
	
	private Boolean addHealthConcernsAndAllergies(Connection conn, int patientId) throws SQLException {
		PatientQuery patientQuery = new PatientQuery(conn);
		Boolean result = patientQuery.addHealthConcernsAndAllergies(patientId, this.healthConcerns.getText(), this.allergies.getText());
		return result;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public void AddHealthConcernsAndAllergies(ActionEvent event) throws SQLException {
		/* ToDo - Should be removed before marking story as done */
		System.out.println("patient's health concerns and allergies successfully added");
		try {
			/*
			 * ToDo - @Nipoon is working on story related to adding health concern and
			 * allergies in the system
			 */
			Connection conn = Driver.getConnection();
			Boolean result = addHealthConcernsAndAllergies(conn, this.patient.getId());
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
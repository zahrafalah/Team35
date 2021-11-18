/**
 * @author Nipoon
 */
package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import database.DoctorQuery;
import database.Driver;
import database.PatientQuery;
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

public class SearchDoctorController {
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


	private Doctor doctor;
	
	public void Logout(ActionEvent event) {
		LogoutController logout = new LogoutController();
		logout.Logout(event);
	}
	
	public void SetPatient(Patient patient) {
		this.patient = patient;
		username.setText(patient.getUsername());
	}
	public void update(ActionEvent event) throws Exception  {
		try {
			Node node = (Node) event.getSource();
		    Stage stage = (Stage) node.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/UpdatePage.fxml"));
	        Parent root = (Parent) loader.load();

	        UpdateController controller = (UpdateController) loader.getController();
	        controller.SetPatient(patient);
	        
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}catch(IOException e) {
			System.err.print(e);
		}
        
	}
	public void home(ActionEvent event) {
		try {
				System.out.print("Home was clicked");
		      Node node = (Node) event.getSource();
		      Stage stage = (Stage) node.getScene().getWindow();
		      FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/PatientView.fxml"));
		      Parent root = (Parent) loader.load();
		      
		      ViewPatientController controller = (ViewPatientController) loader.getController();
		      controller.SetPatient(patient);
		      
		      Scene scene = new Scene(root);
		      stage.setScene(scene);
		      stage.show();

		    } catch (Exception exp) {
		      System.err.println(exp.getMessage());
		    }
	}
	
	
	private Doctor searchDoctor(Connection conn) throws Exception {
		DoctorQuery doctorQuery = new DoctorQuery(conn);
		Doctor doctor = doctorQuery.searchDoctor(this.firstName.getText(), this.lastName.getText());
		return doctor;
	}
	
	public void SearchDoctor(ActionEvent event) throws Exception {
		try {
			Connection conn = Driver.getConnection();
			doctor = searchDoctor(conn);
			if (doctor == null) {
				this.errorMessage.setText("Doctor not found. Try again!");
				return;
			}
			System.out.println("Doctor found with id: " + doctor.getUsername());
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
		      FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/messaging.fxml"));
		      Parent root = (Parent) loader.load();
		      
		      MessagingController controller = (MessagingController) loader.getController();
		      controller.setPatient(patient);
		      controller.setDoctor(doctor);
		      
		      
		      Scene scene = new Scene(root);
		      stage.setScene(scene);
		      stage.show();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}

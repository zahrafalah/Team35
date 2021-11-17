/**
 * @author Nipoon
 */

package controllers;

import java.io.IOException;
import java.util.*;

import database.Driver;
import database.PatientQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import models.Patient;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UpdateController {
	private Patient patient;
	
	@FXML
	private TextField firstname;	
	@FXML
	private TextField lastname;
	@FXML
	private TextField email;
	@FXML
	private TextField insurance;
	@FXML
	private TextField phoneno;
	@FXML
	private DatePicker dob;
	@FXML
	private Label username;
	@FXML
	private Label status;
	@FXML
	private TextField immuno;
	@FXML
	private DatePicker dateOfI;
	
	public void Logout(ActionEvent event) {
		System.out.print("Logiut was clicked");
		LogoutController logout = new LogoutController();
		logout.Logout(event);
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
	
	public void SetPatient(Patient patient) {
		this.patient = patient;
		username.setText(patient.getUsername());
	}
	
	public void updateProfile(ActionEvent event) throws Exception {
//		System.out.println("Form is Submitted Successfully");
		try {
//			System.out.println("FirstName : " + this.firstname.getText());
//			System.out.println("LastName : " + this.lastname.getText());
//			System.out.println("Email : " + this.email.getText());
//			System.out.println("Insurance : " + this.insurance.getText());
//			System.out.println("Phone : " + this.phoneno.getText());
//			System.out.println("DOB :" + this.dob.getValue().toString());
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Connection conn = Driver.getConnection();
			String patientId = saveUpdate(conn);
//			if (patientId == null) {
//				System.out.println("Update Failed");
//				return;
//			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/PatientView.fxml"));
			Parent root = loader.load();
			ViewPatientController controller = (ViewPatientController) loader.getController();
			controller.SetPatient(patient);
			Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
			return;
			
		}catch(Exception e) {
			System.err.println(e);
		}
	}
//	public void initialize() {
//		this.phoneno.setText("phone");
//		this.insurance
//	}
	
	private String saveUpdate(Connection conn) throws Exception {
		PatientQuery patientQuery = new PatientQuery(conn);
		String result = patientQuery.saveUpdate(this.firstname.getText(), this.lastname.getText(),
				this.email.getText(), this.insurance.getText(), patient.getId());
		return result;
	}
	
	
	

}

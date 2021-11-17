/**
 * @author Nipoon
 */

package controllers;

import java.io.IOException;

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
	private TextArea phoneno;
	@FXML
	private DatePicker dob;
	@FXML
	private Label username;
	@FXML
	private Label status;
	
	public void Logout(ActionEvent event) {
		LogoutController logout = new LogoutController();
		logout.Logout(event);
	}
	
	public void SetPatient(Patient patient) {
		this.patient = patient;
		username.setText(patient.getUsername());
	}
	
	public void updateProfile(ActionEvent event) throws Exception {
//		System.out.println("Form is Submitted Successfully");
		try {
			System.out.println("FirstName : " + this.firstname.getText());
			System.out.println("LastName : " + this.lastname.getText());
			System.out.println("Email : " + this.email.getText());
			System.out.println("Insurance : " + this.insurance.getText());
			System.out.println("Phone : " + this.phoneno.getText());
			System.out.println("DOB :" + this.dob.getValue().toString());
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void home(ActionEvent event) throws Exception{
		try {
			System.out.println("I was clicked");
			Node node = (Node) event.getSource();
		    Stage stage = (Stage) node.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/PatientView.fxml"));
	        Parent root = (Parent) loader.load();

	        ViewPatientController controller = (ViewPatientController) loader.getController();
	        controller.SetPatient(patient);
	        
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}catch(IOException e) {
			System.err.print(e);
		}
	}

}

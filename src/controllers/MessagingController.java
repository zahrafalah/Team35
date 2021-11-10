package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import models.Patient;

public class MessagingController {
	
	@FXML
	private Label patientName;
	
	@FXML
	private Label phoneNumber;
	
	@FXML
	private Label emailId;
	
	@FXML
	private MenuButton userName;
	
	private Patient patient;
	


	public void setPatient(Patient patient) {
		patientName.setText(patient.getUsername());
		phoneNumber.setText(patient.getPhoneNumber());
		emailId.setText(patient.getEmail());
		userName.setText(patient.getUsername());
		
	}
	
	

}

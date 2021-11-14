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
	
	private static String folderpath  = "C:\\Work\\CSE360\\Team35\\src";
	


	public void setPatient(Patient patient) {
		patientName.setText(patient.getUsername());
		phoneNumber.setText(patient.getPhoneNumber());
		emailId.setText(patient.getEmail());
		userName.setText(patient.getUsername());
		
	}
	
	

}

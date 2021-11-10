package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Patient;

public class MessagingController {
	
	@FXML
	private Label patientName;
	
	private Patient patient;
	


	public void setPatient(Patient patient) {
		System.out.println(patient.getUsername());
		patientName.setText("Uday");
	}
	
	

}

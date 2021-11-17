package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Patient;

public class ViewPatientController {
	private Patient patient;
	
	@FXML
	private Label username;
	
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
}

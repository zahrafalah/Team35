/**
 * @author: Yogesh,Zahra
 * {@summary}: This program holds controls of Login UI
 */

/* ToDo - This is a temporary page for development purpose. I'll remove it once actual page is integrated */

package controllers;

import java.sql.Connection;

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
import models.Patient;

public class LoginController {
	@FXML
	private Label lblstatus;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	public void Login(ActionEvent event) {
			try {
				System.out.println("attempting login");
				
				Connection conn = Driver.getConnection();
				
				PatientQuery patientQuery = new PatientQuery(conn);
				Patient patient = patientQuery.getPatient(this.username.getText(), this.password.getText());
				
				if (patient == null) {
					lblstatus.setText("Login Failed");
					return;
				}				
				
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				
				stage.setUserData(patient);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchPatient.fxml"));
				Parent root = (Parent)loader.load();
				
				SearchPatientController controller = (SearchPatientController) loader.getController();
				controller.SetPatient(patient);
				Scene scene = new Scene(root);
				
				stage.setScene(scene);
				stage.show();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
	}
}
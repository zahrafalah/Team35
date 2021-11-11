/**
 * @author: Yogesh,Zahra
 * {@summary}: This program holds controls of Login UI
 */

/* ToDo - This is a temporary page for development purpose. I'll remove it once actual page is integrated */

package controllers;

import java.sql.Connection;

import database.Driver;
import database.PatientQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Patient;

public class LoginController {

	ObservableList list = FXCollections.observableArrayList();

	@FXML
	private ChoiceBox<String> role;

	@FXML
	private Label lblstatus;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	public void initialize() {
		loadData();
	}

	private void selectValue(ActionEvent event) {
		String userRole = role.getValue();
		if(userRole == null ) {
			lblstatus.setText("Please select an item.");
		}
	}

	private void loadData() {
		list.removeAll(list);
		String a ="Patient" ;
		String b = "Doctor";
		String c = "Nurse";
		list.addAll(a,b,c);
		role.getItems().addAll(list);
	}

	private FXMLLoader loginPatient(Connection conn, Stage stage) throws Exception {
		PatientQuery patientQuery = new PatientQuery(conn);
		Patient patient = patientQuery.getPatient(this.username.getText(), this.password.getText());

		if (patient == null) {
			lblstatus.setText("Login Failed");
			return null;
		}

		stage.setUserData(patient);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchPatient.fxml"));
		SearchPatientController controller = (SearchPatientController) loader.getController();
		controller.SetPatient(patient);
		return loader;
	}

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
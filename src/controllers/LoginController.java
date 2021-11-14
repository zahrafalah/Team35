/**
 * @author: Yogesh,Zahra
 * {@summary}: This program holds controls of Login UI
 */

/* ToDo - This is a temporary page for development purpose. I'll remove it once actual page is integrated */

package controllers;

import java.sql.Connection;

import database.DoctorQuery;
import database.Driver;
import database.NurseQuery;
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
import models.Doctor;
import models.Nurse;
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

	private void loadData() {
		list.removeAll(list);
		String a ="Patient" ;
		String b = "Doctor";
		String c = "Nurse";
		list.addAll(a,b,c);
		role.getItems().addAll(list);
	}

	private Patient getPatient(Connection conn) throws Exception {
		PatientQuery patientQuery = new PatientQuery(conn);
		Patient patient = patientQuery.getPatient(this.username.getText(), this.password.getText());
		return patient;
	}
	
	private Nurse getNurse(Connection conn) throws Exception {
		NurseQuery nurseQuery = new NurseQuery(conn);
		Nurse nurse = nurseQuery.getNurse(this.username.getText(), this.password.getText());
		return nurse;
	}
	
	private Doctor getDoctor(Connection conn) throws Exception {
		DoctorQuery doctorQuery = new DoctorQuery(conn);
		Doctor doctor = doctorQuery.getDoctor(this.username.getText(), this.password.getText());
		return doctor;
	}

	public void Login(ActionEvent event) {
		try {
			Connection conn = Driver.getConnection();
			
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			
			Parent root = null;
			
			System.out.println("attempting login");
			
			String userRole = role.getValue();
			Patient patient = null;
			Nurse nurse = null;
			Doctor doctor = null;			
			
			if(userRole == null ) {
				lblstatus.setText("Please select a role to log in.");
				return;
			} else if (userRole == "Patient") {
				patient = getPatient(conn);
				if (patient == null) {
					lblstatus.setText("Login Failed");
					return;
				}
				stage.setUserData(patient);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchPatient.fxml"));	
				root = (Parent)loader.load();

				SearchPatientController controller = (SearchPatientController) loader.getController();
				controller.SetPatient(patient);			
			} else if (userRole == "Nurse") {				
				nurse = getNurse(conn);
				System.out.println(nurse);
				if (nurse == null) {
					lblstatus.setText("Login Nurse Failed");
					return;
				}
				stage.setUserData(nurse);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchPatient.fxml"));	
				root = (Parent)loader.load();

				SearchPatientController controller = (SearchPatientController) loader.getController();
				controller.SetNurse(nurse);		
				
			} else if (userRole == "Doctor") {
				
				doctor = getDoctor(conn);
				System.out.println(doctor);
				if (doctor == null) {
					lblstatus.setText("Login Doctor Failed");
					return;
				}
				stage.setUserData(doctor);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchPatient.fxml"));	
				root = (Parent)loader.load();

				SearchPatientController controller = (SearchPatientController) loader.getController();
				controller.SetDoctor(doctor);		
			}
	
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
}
}
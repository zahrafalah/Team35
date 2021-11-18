package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.Driver;
import database.PatientQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Patient;
import models.PatientInfo;
import models.VisitRecord;

public class ViewPatientController implements Initializable {
	private Patient patient;
	
	@FXML
	private Label username;
	@FXML
	private TableView<PatientInfo> tableView;
	@FXML
	private TableColumn<PatientInfo, String> dateColumn;
	@FXML
	private TableColumn<PatientInfo, String> heathIssueColumn;
	@FXML
	private TableColumn<PatientInfo, String> diagnosisColumn;
	@FXML
	private TableColumn<PatientInfo, String> prescriptionColumn;
//	@FXML
//	private TableColumn<PatientInfo, String> immunizationColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		// TODO Auto-generated method stub
		dateColumn.setCellValueFactory(new PropertyValueFactory<PatientInfo, String>("date"));
		heathIssueColumn.setCellValueFactory( new PropertyValueFactory<PatientInfo, String>("healthIssue"));
		diagnosisColumn.setCellValueFactory( new PropertyValueFactory<PatientInfo, String>("diagnosis"));
		prescriptionColumn.setCellValueFactory( new PropertyValueFactory<PatientInfo, String>("prescription"));
//		immunizationColumn.setCellValueFactory( new PropertyValueFactory<PatientInfo, String>("immunization"));
//		SetPatient(patient); 
		//Load dummy Data
		
	}
	
	public ObservableList<PatientInfo> getRecords(){
		ObservableList<PatientInfo> records = FXCollections.observableArrayList();
		records.add( new PatientInfo("Fever","Dengue","CDSD tablet","21:10:2021"));
		
		ArrayList<PatientInfo> visits;
		try {
			
			visits = getVisits(patient.getId());
			for( PatientInfo val : visits ) {
				System.out.println(val.getDiagnosis() + val.getHealthIssue() + val.getDate() );
				records.add(new PatientInfo(val.getHealthIssue(), val.getDiagnosis(), val.getPrescription(), val.getDate()));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(PatientInfo val : records ) {
			System.out.println(val.getHealthIssue());
		}
		
		return records;
	}
	private ArrayList<PatientInfo> getVisits(int id) throws Exception{
		Connection conn = Driver.getConnection();
		PatientQuery patientQuery = new PatientQuery(conn);
		
		List<VisitRecord> result = patientQuery.getPatientVisitsRecordsHome(id);
		ArrayList<PatientInfo> mres = new ArrayList<>();
		for(VisitRecord rec : result) {
			mres.add(new PatientInfo(rec.getHealthIssue(),rec.getDiagnosis(),rec.getPrescription(),rec.getCreatedAt()));
		}
		
		return mres;
	}
	public void Logout(ActionEvent event) {
		LogoutController logout = new LogoutController();
		logout.Logout(event);
	}
	
	public void messages(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
		    Stage stage = (Stage) node.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchDoctor.fxml"));
	        Parent root = (Parent) loader.load();

	        SearchDoctorController controller = (SearchDoctorController) loader.getController();
	        controller.SetPatient(patient);
	        
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}catch(IOException e) {
			System.err.print(e);
		}
	}
	
	public void SetPatient(Patient patient) {
		this.patient = patient;
		username.setText(patient.getUsername());
		tableView.setItems(getRecords());
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

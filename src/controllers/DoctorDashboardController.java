package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import database.Driver;
import database.PatientQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Doctor;
import models.Patient;
import models.VisitRecord;
import models.Vitals;

public class DoctorDashboardController {

    @FXML
    private Label allergies;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colImmunization;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private Label date;

    @FXML
    private Label diastolic;

    @FXML
    private Label height;
    
    @FXML
    private Label lbDoctorName;

    @FXML
    private Label medicalConditions;

    @FXML
    private Label patientAge;

    @FXML
    private Label patientName;

    @FXML
    private Label systolic;

    @FXML
    private TextArea taDiagnosis;

    @FXML
    private TextArea taHealthIssue;

    @FXML
    private TextArea taPrescription;

    @FXML
    private Label temperature;

    @FXML
    private TableView<?> tvImmunizationHistory;

    @FXML
    private Label weight;
    
    @FXML
	private VBox immunizationContainer;
    
    @FXML
	private VBox visitRecordsPane;
    
    private Patient patient;
    
    private Doctor doctor;
    
    private Vitals vitals;
    
//    @Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//    	mbDoctorName.setText(doctor.getUsername()); 
//		
//	}
    private String getDateAsString (Date date) {
    	String datePattern = "MM/dd/yyyy";
		DateFormat dateFormat = new SimpleDateFormat(datePattern);
		return dateFormat.format(date);
    }
    
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
		lbDoctorName.setText(doctor.getUsername());
		Date today = Calendar.getInstance().getTime();
		String todayAsString = getDateAsString(today);
		date.setText(todayAsString);
	}

	public void setPatient(Patient patient) throws SQLException {
		this.patient = patient;
		setPatientDetails(patient);
	}
	
	private String calculateAge(String dob) {
		LocalDate today = LocalDate.now();                          //Today's date
		LocalDate birthday = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Period p = Period.between(birthday, today);
		return "" + p.getYears();
	}
	
	private void setPatientDetails(Patient patient) throws SQLException {
		patientName.setText(patient.getUsername());
		String age = calculateAge(patient.getDob());
		patientAge.setText(age);
		allergies.setText(patient.getAllergies());
		medicalConditions.setText(patient.getHealthConcerns());
		setTodaysVitals(patient.getId());
		setImmunization(patient.getImmunization());
		setVisitRecords(patient.getId());
		
	}
	
	private void setVisitRecords(int patientId) throws SQLException {
		Connection connection = Driver.getConnection();
		List<VisitRecord> visitRecords = getPatientVisitsRecords(connection, patientId);
		Accordion accordion = new Accordion();
		for (int i = 0; i < visitRecords.size(); i++) {
			if (visitRecords.get(i).getHealthIssue().length() > 0) {
				Label label = new Label();
				label.setText("Health issues - " + visitRecords.get(i).getHealthIssue() + "\nPrescription - "
						+ visitRecords.get(i).getPrescription());
				label.setWrapText(true);
				label.setPadding(new Insets(4));
				TitledPane pane1 = new TitledPane(visitRecords.get(i).getCreatedAt(), label);
				accordion.getPanes().add(pane1);
			}
		}
		visitRecordsPane.getChildren().add(accordion);
	}
	
	private List<VisitRecord> getPatientVisitsRecords(Connection connection, int patientId) throws SQLException {
		PatientQuery patientQuery = new PatientQuery(connection);
		List<VisitRecord> visitRecords = patientQuery.getPatientVisitsRecords(patientId);
		return visitRecords;
	}
	
	private void setImmunization(String immunization) {
		String[] immunizations = immunization.split(",");
		for (int i = 0; i < immunizations.length; i++) {
//			String[] immunizationDetails = immunizations[i].split(":");
//			System.out.println(immunizationDetails[0]);
//			System.out.println(immunizationDetails[1]);
			Label label = new Label(immunizations[i]);
			label.setPadding(new Insets(4));
			immunizationContainer.getChildren().add(label);
		}
	}
	
	private void setTodaysVitals(int patientID) {
		Connection connection = Driver.getConnection();
		PatientQuery patientQuery = new PatientQuery(connection);
		String vitalQuery = patientQuery.getVitalsQuery(patientID);
		System.out.println(vitalQuery);
		Statement statement;
		ResultSet resultSet;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(vitalQuery);
			while(resultSet.next()) {
				vitals = new Vitals(resultSet.getInt("_id"),resultSet.getInt("patientID"),
						resultSet.getInt("weight"),resultSet.getInt("height"),
						resultSet.getInt("temperature"),resultSet.getInt("systolic"),
						resultSet.getInt("diastolic"));
				
			}
			
			height.setText("" + vitals.getHeight() + " cm");
			weight.setText("" + vitals.getWeight() + " kg");
			temperature.setText("" + vitals.getTemperature() + " °C");
			systolic.setText("" + vitals.getSystolic() + " mmHg");
			diastolic.setText("" + vitals.getDiastolic() + " mmHg");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

    @FXML
    void deletePatient(ActionEvent event) throws IOException {
    	Connection connection = Driver.getConnection();
    	PatientQuery patientQuery = new PatientQuery(connection);
		String deletePatientQuery = patientQuery.deletePatientQuery(patient.getId());
		System.out.println(deletePatientQuery);
		Statement statement;
		
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Delete Confirmation");
    	alert.setHeaderText("Delete Patient:");
    	alert.setContentText("Are you sure you want to Delete this Patient? You will be redirected to Search Patient.");
    	Optional <ButtonType> action = alert.showAndWait();
    	
    	Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
    	
    	if(action.get() == ButtonType.OK) {
    		try{
                statement = connection.createStatement();
                statement.executeUpdate(deletePatientQuery);
            }catch(Exception ex){
                ex.printStackTrace();
            }    		
    		System.out.println("Patient Deleted");
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/SearchPatient.fxml"));
			Parent root = loader.load();
			SearchPatientController searchPatientController = (SearchPatientController) loader.getController();
			searchPatientController.SetDoctor(doctor);
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
    	}else {
    		System.out.println("Patient Deletion Cancel");
    	}
    }
    
    @FXML
    void logOut(ActionEvent event) {
    	LogoutController logout = new LogoutController();
		logout.Logout(event);
    }

    @FXML
    void redirectToMessages(ActionEvent event) {
    }

    @FXML
    void saveDiagnosis(ActionEvent event) throws SQLException {
    	Connection connection = Driver.getConnection();
    	PatientQuery patientQuery = new PatientQuery(connection);
		String saveDiagnosisQuery = patientQuery.getSaveDiagnosisQuery(vitals.getId(), taHealthIssue.getText(),
																taDiagnosis.getText(),taPrescription.getText());
		System.out.println(saveDiagnosisQuery);
		Statement statement;
		try{
            statement = connection.createStatement();
            statement.executeUpdate(saveDiagnosisQuery);
        }catch(Exception ex){
            ex.printStackTrace();
        }
		setVisitRecords(this.patient.getId());
		taHealthIssue.setText("");
		taDiagnosis.setText("");
		taPrescription.setText("");
    }

    @FXML
    void sendPrescriptionToPharmacy(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Send Prescription to Pharmacy");
    	alert.setHeaderText("Pharmacy Prescription");
    	alert.setContentText("System will send prescription to the patient's preffered pharmacy.");
    	Optional <ButtonType> action = alert.showAndWait();
    }

}

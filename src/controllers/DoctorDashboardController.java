package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import database.Driver;
import database.PatientQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import models.Doctor;
import models.Patient;
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

	public void setPatient(Patient patient) {
		this.patient = patient;
		setPatientDetails(patient);
	}
	
	private String calculateAge(String dob) {
		LocalDate today = LocalDate.now();                          //Today's date
		LocalDate birthday = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Period p = Period.between(birthday, today);
		return "" + p.getYears();
	}
	
	private void setPatientDetails(Patient patient) {
		patientName.setText(patient.getUsername());
		String age = calculateAge(patient.getDob());
		patientAge.setText(age);
		setTodaysVitals(patient.getId());
	}
	
	private void setTodaysVitals(int patientID) {
		Connection connection = Driver.getConnection();
		PatientQuery patientQuery = new PatientQuery(connection);
		String vitalQuery = patientQuery.getVitalsQuery(patientID);
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
    void deletePatient(ActionEvent event) {

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
    void saveDiagnosis(ActionEvent event) {

    }

    @FXML
    void sendPrescriptionToPharmacy(ActionEvent event) {

    }

}

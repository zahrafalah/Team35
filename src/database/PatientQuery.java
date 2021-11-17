package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Patient;
import models.VisitRecord;



public class PatientQuery {

	private Connection conn;


//	private String getPatientsQueryStmt = "select * from patients";
	private String getOnePatientQuery(String username, String pass) {
		return String.format(
			"SELECT * FROM  patients WHERE username = '%s' AND password = '%s'", username, pass);
	}
	
	private String getDuplicateQuery(String username) {
		return String.format(
			"SELECT * FROM  patients WHERE username = '%s' ", username);
	}
	
	private String createPatientQuery(String firstname, String lastname, String username,String dob,  String pass ) {
		return String.format(
			"INSERT INTO patients(firstname, lastname, username, dob, password) VALUES('%s','%s','%s','%s','%s');", firstname, lastname, username,dob, pass);
	}
	
	private String searchOnePatientQuery(String firstname, String lastname, String dob) {
		return String.format(
			"SELECT * FROM  patients WHERE firstname = '%s' AND lastname = '%s' AND dob = '%s'", firstname, lastname, dob);
	}
	
	private String saveVitalsQuery(String weight, String height, String temperature, String bpHigh, String bpLow, Boolean disableBP) {
		if(disableBP) {
			return String.format(
					"INSERT INTO vitals(weight, height, temperature) VALUES(%s,%s,%s);", weight, height, temperature);
		}
		return String.format(
			"INSERT INTO vitals(weight, height, temperature, systolic , diastolic) VALUES(%s,%s,%s,%s,%s);", weight, height, temperature, bpHigh, bpLow);
	}
	
	private String addHealthConcernsAndAllergiesQuery(int patientId, String healthConcerns, String allergies) {
		return String.format(
			"UPDATE patients SET healthConcerns = %s, allergies = %s WHERE _id = '%s';",healthConcerns, allergies, patientId);
	}
	
	private String createVisitQuery(String patientId, String vitalId, String strDate) {
		return String.format(
			"INSERT INTO visits(patientId, vitalId, createdAt) VALUES('%s','%s', '%s');", patientId, vitalId, strDate);
	}
	
	private String getPatientDataQuery(int patientId) {
		return String.format(
				"SELECT * FROM  patients WHERE _id = '%s';", patientId);
	}
	
	private String getPatientVisitsRecordsQuery(int patientId) {
		return String.format(
				"SELECT * FROM  visits WHERE patientId = '%s';", patientId);
	}
	private String saveUpdateQuery(String firstname, String lastname, String email, String phone, int patientID) {
		System.out.println(phone);
		return String.format(
				"UPDATE patients SET firstname ='%s', lastname = '%s', emailID = '%s', phoneno = '%s' WHERE _id = %d ; ", firstname, lastname, email, phone, patientID);
	}


//	private String deleteOnePatientQueryStmt = "DELETE FROM patients"+ "WHERE _id = " + userId;
//	private String addPatientQueryStmt = "INSERT INTO patients(username,password) VALUES ('', '');";
//	private String updatePatientQueryStmt = "UPDATE patients SET "+ "username = '" + "'" + "WHERE"+ " _id = "+ userId +";";

	public PatientQuery(Connection conn) {
		this.conn = conn;

	}
	public boolean isDuplicateFound(String username) throws SQLException{
		
		String query = getDuplicateQuery(username);
		System.out.println(query);
		PreparedStatement preparedStatment = this.conn.prepareStatement(query);
		ResultSet resultSet = preparedStatment.executeQuery();
		
		if (resultSet.next()) {
			return true;
		}
		
		return false;
		
	}

	public Patient getPatient(String username, String password) throws SQLException {
		Patient patient = null;
		String query = getOnePatientQuery(username, password);
		System.out.println(query);
		PreparedStatement preparedStatment = this.conn.prepareStatement(query);
		ResultSet resultSet = preparedStatment.executeQuery();
		if (resultSet.next()) {
			int id = resultSet.getInt("_id");
			String name = resultSet.getString("username");
			String pass = resultSet.getString("password");
			patient = new Patient(name, pass, id);
		}
		return patient;
	}
	
	public String saveVitals(String weight, String height, String temperature, String bpHigh, String bpLow, Boolean disableBP) throws SQLException {
		String result = null;
		try {
			String query = saveVitalsQuery(weight, height, temperature, bpHigh, bpLow, disableBP);
			System.out.println(query);
			PreparedStatement preparedStatement = this.conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				result = Integer.toString(generatedKeys.getInt(1));
			}

		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return result;
	}
	public String saveUpdate(String firstname, String lastname, String email, String phone, int patientID)throws SQLException {
		String result = null;
		
		try {
			String query = saveUpdateQuery(firstname, lastname,email, phone, patientID);
			System.out.println(query);
			PreparedStatement preparedStatement = this.conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				result = Integer.toString(generatedKeys.getInt(1));
			}
		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return result;
	}
	
	
	public Boolean addHealthConcernsAndAllergies(int patientId, String healthConcerns, String allergies) throws SQLException {
		Boolean result = false;
		try {
			String query = addHealthConcernsAndAllergiesQuery(patientId, healthConcerns, allergies);
			System.out.println(query);
			PreparedStatement preparedStatement = this.conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				result = true;
			}

		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return result;
	}
	
	public String createVisit(String patientId, String vitalId) throws SQLException {
		String result = null;
		try {
			Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String strDate = dateFormat.format(date); 
			String query = createVisitQuery(patientId, vitalId, strDate);
			System.out.println(query);
			PreparedStatement preparedStatement = this.conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				result = Integer.toString(generatedKeys.getInt(1));
			}

		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return result;
	}
	
	public Patient searchPatient(String firstname, String lastname, String dob) throws SQLException {
		Patient patient = null;
		try {
			String query = searchOnePatientQuery(firstname, lastname, dob);
			System.out.println(query);
			PreparedStatement preparedStatment = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("_id");
				String name = resultSet.getString("username");
				patient = new Patient(name, id, dob);
			}

		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return patient;
	}
	
	public List<String> getPatientData(int patientId) throws SQLException {
		List<String> patientData = new ArrayList<String>(3);
		try {
			String query = getPatientDataQuery(patientId);
			System.out.println(query);
			PreparedStatement preparedStatment = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {
				patientData.add(resultSet.getString("immunization"));
				patientData.add(resultSet.getString("healthConcerns"));
				patientData.add(resultSet.getString("allergies"));
			}

		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return patientData;
	}
	
	public List<VisitRecord> getPatientVisitsRecords(int patientId) throws SQLException {
		List<VisitRecord> visitRecords = new ArrayList<VisitRecord>();
		try {
			String query = getPatientVisitsRecordsQuery(patientId);
			System.out.println(query);
			PreparedStatement preparedStatment = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatment.executeQuery();

			while (resultSet.next()) {
				VisitRecord record = new VisitRecord(
						resultSet.getString("createdAt"),
						resultSet.getString("healthIssue"),
						resultSet.getString("prescription")
				);
				visitRecords.add(record);
			}
		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		return visitRecords;
	}
	
	public boolean createPatient( String firstname, String lastname, String username, String dob, String password) throws SQLException {
		String query = createPatientQuery(firstname, lastname, username, dob, password);
		System.out.println(query);
		
		PreparedStatement preparedStatment = this.conn.prepareStatement(query);
		preparedStatment.executeUpdate();
		return true;
	}
}

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import models.Patient;

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
	
	public boolean createPatient( String firstname, String lastname, String username, String dob, String password) throws SQLException {
		String query = createPatientQuery(firstname, lastname, username, dob, password);
		System.out.println(query);
		
		PreparedStatement preparedStatment = this.conn.prepareStatement(query);
		preparedStatment.executeUpdate();
		return true;
	}
}

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class PatientQuery {
	
	private Connection conn;
	
	
//	private String getPatientsQueryStmt = "select * from patients";
	private String getOnePatientQuery(String username, String pass) {
		return String.format(
			"SELECT * FROM  patients WHERE username = '%s' AND password = '%s'", username, pass);
	}
		
	
//	private String deleteOnePatientQueryStmt = "DELETE FROM patients"+ "WHERE _id = " + userId;
//	private String addPatientQueryStmt = "INSERT INTO patients(username,password) VALUES ('', '');";
//	private String updatePatientQueryStmt = "UPDATE patients SET "+ "username = '" + "'" + "WHERE"+ " _id = "+ userId +";";
	
	public PatientQuery(Connection conn) {
		this.conn = conn;
	
	}
	
	public Patient getPatient(String username, String password) throws SQLException {
		Patient patient = null;
		try {
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
			
		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}
		
		return patient;
		
	}
	
}

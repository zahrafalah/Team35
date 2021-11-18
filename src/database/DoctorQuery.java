package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Doctor;

public class DoctorQuery {
	private Connection conn;

	public DoctorQuery(Connection conn) {
		this.conn = conn;

	}
	private String getOneDoctorQuery(String username, String pass) {
		return String.format(
			"SELECT * FROM  doctors WHERE username = '%s' AND password = '%s'", username, pass);
	}

	public Doctor getDoctor(String username, String password) throws SQLException {
		Doctor doctor = null;
		try {
			String query = getOneDoctorQuery(username, password);
			System.out.println(query);
			PreparedStatement preparedStatment = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("_id");
				String name = resultSet.getString("username");
				String pass = resultSet.getString("password");
				doctor = new Doctor(name, pass, id);
			}

		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}

		return doctor;
	}
	public String searchDoctorQuery(String firstName, String lastName) {
		return String.format(
				"SELECT * FROM  doctors WHERE username = '%s' OR username = '%s'", firstName, lastName); 
	}
	
	public Doctor searchDoctor(String firstName, String lastName) {
		Doctor doctor = null;
		try {
			String query = searchDoctorQuery(firstName, lastName);
			PreparedStatement preparedStatment = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("_id");
				String name = resultSet.getString("username");
				doctor = new Doctor(id, name);
			}
		}catch(Exception exp) {
			exp.printStackTrace();
		}

		return doctor;
	}
}

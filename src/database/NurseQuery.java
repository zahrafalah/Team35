package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Nurse;

public class NurseQuery {
	private Connection conn;

	public NurseQuery(Connection conn) {
		this.conn = conn;

	}
	private String getOneNurseQuery(String username, String pass) {
		return String.format(
			"SELECT * FROM nurses WHERE username = '%s' AND password = '%s'", username, pass);
	}

	public Nurse getNurse(String username, String password) throws SQLException {
		Nurse nurse = null;
		try {
			String query = getOneNurseQuery(username, password);
			System.out.println(query);
			PreparedStatement preparedStatment = this.conn.prepareStatement(query);
			ResultSet resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("_id");
				String name = resultSet.getString("username");
				String pass = resultSet.getString("password");
				nurse = new Nurse(name, pass, id);
			}

		}catch(Exception exp) {
			exp.printStackTrace();
			throw exp;
		}

		return nurse;
	}


}

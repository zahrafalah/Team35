package application;

import java.sql.*;


public class Driver {
	private Connection conn;
	private String url;
	private String username;
	private String password;
	private String database;
	
	public Driver(String url, String database, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
		this.database = database;
		
		Connection connection = this.createConnection();
		this.setConnection(connection);
		
	}
	
	private Connection getConnection() {
		return this.conn;
	}
	
	private void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	private Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.url+ this.database, this.username , this.password);
			
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return conn;
	}
	
	public ResultSet getPatients() throws Exception {
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from patients");
			return results;
		}catch(Exception exc) {
			throw exc;
		}
		
	}
	
	
}

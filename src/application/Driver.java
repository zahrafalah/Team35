package application;

import java.sql.*;


public class Driver {
	private static Connection conn;
	private String url;
	private String hostUsername;
	private String hostPassword;
	private String database;
	
	
	public Driver(String url, String database, String username, String password){
		this.url = url;
		this.hostUsername = username;
		this.hostPassword = password;
		this.database = database;
		
		Connection connection = this.createConnection();
		this.setConnection(connection);
		
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	private void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	private Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.url+ this.database, this.hostUsername , this.hostPassword);
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return conn;
	}
}

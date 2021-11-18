/**
 * @author: Zahra
 * {@summary}: Creates a database connection
 */
package database;

import java.sql.*;

public class Driver {
  // (static )I do not need to make an instance of a driver to get access to conn;
  private static Connection conn;
  private String url;
  private String hostUsername;
  private String hostPassword;
  private String database;

  public Driver(String url, String database, String username, String password) {
    this.url = url;
    this.hostUsername = username;
    this.hostPassword = password;
    this.database = database;

    Connection connection = this.createConnection();
    this.setConnection(connection);

  }

  public static Connection getConnection() {
    return conn;
  }

  private void setConnection(Connection conn) {
    this.conn = conn;
  }

  private Connection createConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(this.url + this.database, this.hostUsername, this.hostPassword);
      System.out.println("Connection created");
    } catch (Exception exc) {
      exc.printStackTrace();
    }

    return conn;
  }
}

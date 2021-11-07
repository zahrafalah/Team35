package application;
	
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
		Driver driver = new Driver("jdbc:mysql://localhost:3306/","office", "root", "password");
		Connection conn = driver.getConnection();
		try {
			PatientQuery patientQuery = new PatientQuery(conn);
			Patient patient = patientQuery.getPatient("Zahra", "123456");
			System.out.println(patient.getUsername());
			System.out.println(patient.getId());
		}catch(Exception exc) {
			System.out.println(exc);
		}
	}
}

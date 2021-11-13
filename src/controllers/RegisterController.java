package controllers;

import java.sql.Connection;
import java.sql.SQLException;

import database.Driver;
import database.PatientQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Patient;

public class RegisterController {
  @FXML
  private Label status;

  @FXML
  private TextField firstname;

  @FXML
  private TextField lastname;

  @FXML
  private TextField password;

  @FXML
  private DatePicker dob;

  @FXML
  private TextField username;

  @FXML
  private TextField confirmPass;

  public void Register(ActionEvent event) {
    System.out.println("Hi");

    try {
      boolean patient = false;
      Connection conn = Driver.getConnection();

      System.out.println("pass: " + this.password.getText());
      System.out.println("confirmpass: " + this.confirmPass.getText());

      boolean passCheck = this.password.getText().equals(this.confirmPass.getText());
      System.out.println("check: " + passCheck);

      if (!passCheck) {
        System.out.println("passwords don't match");
        status.setText("Password doesn't match");
        return;
      }

      PatientQuery patientQuery = new PatientQuery(conn);
      boolean isFound = patientQuery.isDuplicateFound(this.username.getText());

      if (isFound == true) {
        status.setText("Username must be unique!");
        return;
      }

      System.out.println("isFound " + isFound);

      patient = patientQuery.createPatient(this.firstname.getText(), this.lastname.getText(), this.username.getText(),
          this.dob.getValue().toString(), this.password.getText());
      if (patient == true) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/Login.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      }

    } catch (Exception exp) {
      System.out.println(exp);
    }
  }
}

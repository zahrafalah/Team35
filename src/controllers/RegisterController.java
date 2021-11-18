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
import javafx.scene.control.PasswordField;
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
  private PasswordField password;

  @FXML
  private DatePicker dob;

  @FXML
  private TextField username;

  @FXML
  private PasswordField confirmPass;

  public void Register(ActionEvent event) {
    try {
      boolean patient = false;
      Connection conn = Driver.getConnection();

      boolean passCheck = this.password.getText().equals(this.confirmPass.getText());

      if (this.firstname.getText() == "" || this.lastname.getText() == "" || this.username.getText() == ""
          || this.password.getText() == "" || this.confirmPass.getText() == "") {
        status.setText("All fields are required");
        return;
      }

      if (this.dob.getValue() == null) {
        status.setText("Date of birth is required");
        return;
      }

      if (!passCheck) {
        status.setText("Password doesn't match");
        return;
      }

      PatientQuery patientQuery = new PatientQuery(conn);
      boolean isFound = patientQuery.isDuplicateFound(this.username.getText());

      if (isFound == true) {
        status.setText("Username must be unique!");
        return;
      }

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

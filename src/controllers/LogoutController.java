package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogoutController {

  public void Logout(ActionEvent event) {
    try {
      System.out.println("attempting logout");
      Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();

      Parent root = FXMLLoader.load(getClass().getResource("/pages/Login.fxml"));
      stage.setScene(new Scene(root));
      stage.show();

    } catch (Exception ex) {
      System.err.println(ex.getMessage());

    }
  }

}

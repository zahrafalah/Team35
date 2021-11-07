/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Login UI
 */

/* ToDo - This is a temporary page for development purpose. I'll remove it once actual page is integrated */

package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	public void Login(ActionEvent event) {
		if (username.getText().equals("user") && password.getText().equals("pass")) {
			System.out.println("successful login");
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pages/SearchPatient.fxml")));
				stage.setScene(scene);
				stage.show();

			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		} else {
			System.out.println("unsuccessful login");
		}
	}
}
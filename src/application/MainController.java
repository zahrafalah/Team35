package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private Label lblstatus;
	
	@FXML
	private  TextField txtusername;
	
	@FXML
	private TextField txtPassword;
	
	public void Login(ActionEvent event) {
		if(txtusername.getText().equals("user") && txtPassword.getText().equals("pass")) {
			lblstatus.setText("Login Success");
		}else {
			lblstatus.setText("Login Failed");
		}
	}
}
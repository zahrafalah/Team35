/**
 * @author: Yogesh
 * {@summary}: This program holds controls of Add Vitals UI
 */

/* ToDo - Should update UI as per patient's age */

package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVitalsController {

	@FXML
	private TextField weight;

	@FXML
	private TextField height;
	
	@FXML
	private TextField temperature;

	@FXML
	private TextField bpHigh;
	
	@FXML
	private TextField bpLow;

	public void AddVitals(ActionEvent event) {
		/* ToDo - Should be removed before marking story as done */
		System.out.println("patient's vitals successfully added");
		try {
			/* ToDo - should save patient's vitals before redirecting to next page */
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pages/HealthConcernsAndAllergies.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
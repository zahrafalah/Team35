package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import models.Doctor;
import models.Nurse;
import models.Patient;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MessagingController {
	
	@FXML
	private Label patientName;
	
	@FXML
	private Label phoneNumber;
	
	@FXML
	private Label emailId;
	
	
	@FXML
	private MenuButton userName;
	
	@FXML
	private Label nurseName;
	
	@FXML
	private Label doctorName;
	
	@FXML
	private TextArea messageContent;
	
	@FXML
	private TextArea displayMessages;
	
	private Patient patient;
	
	private String role;
	
	private Doctor doctor;
	
	private Nurse nurse;
	
	private String currentUserName;
	
	
	public void setRole(String role) {
		this.role = role;
	}
	
	private String getRole() {
		return this.role;
	}
	
	private static String folderpath  = "C:\\Work\\CSE360\\Team35\\src\\messages";
	


	public void setPatient(Patient patient) {
		patientName.setText(patient.getUsername());
		phoneNumber.setText(patient.getPhoneNumber());
		emailId.setText(patient.getEmail());
		userName.setText(patient.getUsername());
		currentUserName = patient.getUsername();
		checkOrCreateSourceFile();
		checkOrCreateSourceFileForTarget();
	}
	
	public void setDoctor(Doctor doctor) {
		doctorName.setText(doctor.getUsername());
		currentUserName = doctor.getUsername();
		checkOrCreateSourceFile();
		checkOrCreateSourceFileForTarget();
	}
	
	public void setNurse(Nurse nurse) {
		nurseName.setText(nurse.getUsername());
		currentUserName = nurse.getUsername();
		checkOrCreateSourceFile();
		checkOrCreateSourceFileForTarget();
	}
	
	public Doctor getAssignedDoctor() {
		Doctor docotr = new Doctor("Yogesh","123456",1);
		return docotr;
	}
	
	
	private void checkOrCreateSourceFile() {
		this.displayMessages.setEditable(false);
		//File usertxtFile = new File(folderpath+"\\"+getRole()+"\\"+currentUserName+".txt");
		try {
		      File myObj = new File("C:\\Work\\CSE360\\Team35\\src\\messagesTxt\\" + getRole() + "\\"+currentUserName+".txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		    	  String sourceMsgs = "";
		    	  Scanner myReader = new Scanner(myObj);
		          while (myReader.hasNextLine()) {
		            String data = myReader.nextLine();
		            sourceMsgs = sourceMsgs + "\n" + data;
		          }
		          myReader.close();
		          this.displayMessages.setText(sourceMsgs.trim());
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	private void checkOrCreateSourceFileForTarget() {
		this.displayMessages.setEditable(false);
		try {
		      File myObj = new File("C:\\Work\\CSE360\\Team35\\src\\messagesTxt\\Doctor\\"+getAssignedDoctor().getUsername()+".txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		    	  System.out.println("Created");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void sendMessage(ActionEvent event) {
		
		String totalMessage = this.displayMessages.getText();
		if(this.messageContent.getText() != null) {
			try {
				String SourceFile = "C:\\Work\\CSE360\\Team35\\src\\messagesTxt\\Patient\\"+currentUserName+".txt";
				String TargetFile = "C:\\Work\\CSE360\\Team35\\src\\messagesTxt\\Doctor\\"+getAssignedDoctor().getUsername()+".txt";
				File myObj = new File(SourceFile);
				String sourceMsgs = "";
		    	  Scanner myReader = new Scanner(myObj);
		          while (myReader.hasNextLine()) {
		            String data = myReader.nextLine();
		            sourceMsgs = sourceMsgs + "\n" + data;
		          }
		          myReader.close();
		          sourceMsgs = sourceMsgs + "\n" + currentUserName + ": "  + this.messageContent.getText();
		          this.messageContent.setText("");
		          this.displayMessages.setText(sourceMsgs.trim());
		          FileWriter myWriter = new FileWriter(SourceFile);
		          myWriter.write(sourceMsgs.trim());
		          myWriter.close();
		          FileWriter Writer = new FileWriter(TargetFile);
		          Writer.write(sourceMsgs.trim());
		          Writer.close();
			}
			catch(Exception e) {
				
			}
		}
		
	}
	

}

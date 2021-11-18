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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


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
	
	private static String folderpath  = "C:\\Work\\CSE360\\Team35\\src\\messages\\";
	


	public void setPatient(Patient patient) {
		patientName.setText(patient.getUsername());
		currentUserName = patient.getUsername();
		checkOrCreateSourceFile();
		checkOrCreateSourceFileForTarget();
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
		return this.doctor;
	}
	
	
	public Patient getAssignedPatient() {
		Patient pat = new Patient("Uday","123456",12);
		return pat;
	}
	
	
	private void checkOrCreateSourceFile() {
		this.displayMessages.setEditable(false);
		try {
		      File myObj = new File(folderpath + getRole() + "\\"+currentUserName+".txt");
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
			  if(getRole() == "Patinet") {
			      File myObj = new File(folderpath + "Doctor\\"+getAssignedDoctor().getUsername()+".txt");
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());
			      } else {
			    	  System.out.println("Created");
			      }
			  }
			  else {
				  File myObj = new File(folderpath+"Patient\\"+getAssignedPatient().getUsername()+".txt");
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());
			      } else {
			    	  System.out.println("Created");
			      }
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
				String SourceFile = folderpath+"Patient\\"+currentUserName+".txt";
				String TargetFile = folderpath+"Doctor\\"+getAssignedDoctor().getUsername()+".txt";
				String msgRole = getRole();
				if(msgRole == "Patient") {
					SourceFile = folderpath+"Patient\\"+currentUserName+".txt";
					TargetFile = folderpath+"Doctor\\"+getAssignedDoctor().getUsername()+".txt";
				}
				else {
					SourceFile = folderpath+"Doctor\\"+currentUserName+".txt";
					TargetFile = folderpath+"Patient\\"+getAssignedPatient().getUsername()+".txt";
				}
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
				System.out.println(e);
			}
		}
		
	}
	

}

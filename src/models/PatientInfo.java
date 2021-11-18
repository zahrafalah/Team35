/**
 * @author Nipoon
 */
package models;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class PatientInfo {
	private SimpleStringProperty healthIssue, diagnosis, prescription, date;
//	private LocalDate date;
	
	public PatientInfo(String healthIssue, String diagnosis,
			 String prescription, String date) {
		super();
		this.healthIssue = new SimpleStringProperty(healthIssue);
		this.diagnosis = new SimpleStringProperty(diagnosis);
//		this.immunization = new SimpleStringProperty(immunization);
		this.prescription = new SimpleStringProperty(prescription);
		this.date = new SimpleStringProperty(date);
	}

	public String getHealthIssue() {
		return healthIssue.get();
	}

	public void setHealthIssue(SimpleStringProperty healthIssue) {
		this.healthIssue = healthIssue;
	}

	public String getDiagnosis() {
		return diagnosis.get();
	}

	public void setDiagnosis(SimpleStringProperty diagnosis) {
		this.diagnosis = diagnosis;
	}

//	public String getImmunization() {
//		return immunization.get();
//	}
//
//	public void setImmunization(SimpleStringProperty immunization) {
//		this.immunization = immunization;
//	}

	public String getPrescription() {
		return prescription.get();
	}

	public void setPrescription(SimpleStringProperty prescription) {
		this.prescription = prescription;
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}
	
	
	
}

package models;

public class VisitRecord {
	private String createdAt;
	private String healthIssue;
	private String prescription;
	private String diagnosis;
	
	public VisitRecord(String createdAt, String healthIssue, String prescription) {
		this.createdAt = createdAt;
		this.healthIssue = healthIssue;
		this.prescription = prescription;
	}
	
	public VisitRecord(String createdAt, String healthIssue, String prescription, String diagnosis) {
		this.createdAt = createdAt;
		this.healthIssue = healthIssue;
		this.prescription = prescription;
		this.diagnosis = diagnosis;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setHealthIssue(String healthIssue) {
		this.healthIssue = healthIssue;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}
	
	public String getHealthIssue() {
		return this.healthIssue;
	}
	
	public String getPrescription() {
		return this.prescription;
	}
}

package models;

public class VisitRecord {
	private String createdAt;
	private String healthIssue;
	private String prescription;
	
	public VisitRecord(String createdAt, String healthIssue, String prescription) {
		this.createdAt = createdAt;
		this.healthIssue = healthIssue;
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

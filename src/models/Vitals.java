package models;

public class Vitals {
	private int id;
	private int patientID;
	private int weight;
	private int height;
	private int temperature;
	private int systolic;  
	private int diastolic;
	
	public Vitals(int id, int patientID, int weight, int height, int temperature, int systolic, int diastolic) {
		super();
		this.id = id;
		this.patientID = patientID;
		this.weight = weight;
		this.height = height;
		this.temperature = temperature;
		this.systolic = systolic;
		this.diastolic = diastolic;
	}
	public int getId() {
		return id;
	}
	public int getpatientId() {
		return patientID;
	}
	
	public int getWeight() {
		return weight;
	}
	public int getHeight() {
		return height;
	}
	public int getTemperature() {
		return temperature;
	}
	public int getSystolic() {
		return systolic;
	}
	public int getDiastolic() {
		return diastolic;
	}
	
	
}

/**
 * @author: Zahra
 * {@summary}: Class to represent a Patient
 */
package models;

public class Patient {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String phoneNumber;
	private String dob;
	private String immunization;
	private String healthConcerns;
	private String allergies;

	public Patient(int id, String firstName, String lastName, String username, String password, String dob,
			String immunization, String healthConcerns, String allergies) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.immunization = immunization;
		this.healthConcerns = healthConcerns;
		this.allergies = allergies;
	}
	
	public Patient(String username, String pass, int id) {
		this.username= username;
		this.password = pass;
		this.id = id;
	}
	
	public Patient(String username, int id, String dob) {
		this.username= username;
		this.id = id;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getImmunization() {
		return immunization;
	}

	public String getHealthConcerns() {
		return healthConcerns;
	}

	public String getAllergies() {
		return allergies;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getDob() {
		return dob;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
}

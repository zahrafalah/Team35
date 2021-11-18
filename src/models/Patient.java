/**
 * @author: Zahra
 * {@summary}: Class to represent a Patient
 */
package models;

public class Patient {
	private int id;
	private String username;
	private String password;
	private String phoneNumber;
	private String dob;
	private String firstName;
	private String secondName;
	private String email;

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
	
	public String getSecondName() {
		return secondName;
	}
	
	public String getEmail() {
		return email;
	}
}

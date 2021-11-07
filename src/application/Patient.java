package application;

public class Patient {
	private int id;
	private String username;
	private String password;
	
	public Patient(String username, String pass, int id) {
		this.username= username;
		this.password = pass;
		this.id = id;
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
}

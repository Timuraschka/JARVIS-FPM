package edu.fra.uas.model;



import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	@GeneratedValue
	private long User_Id;
	
	private String first_name;
	private String last_name;
	
	private String username;
	private String password;
	private String email;
	
	public User() {
		super();
	}
	
	
	
	
	
	
	
	
	
	
	

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

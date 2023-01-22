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

	public boolean validatePassword(String password) {					//requirements have been set for creating a new password 
	    String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	    if (password.matches(pattern)) {
	        return true;
	    }
	    return false;
	}

	public void setPassword(String password) {  				//password can be set after the validation has checked that the password matches the requirements
	    if (validatePassword(password)) {
	        this.password = password;
	    } else {
	        throw new IllegalArgumentException("Invalid password. Passwords must be at least 8 characters long "
	        	+ "and contain at least one uppercase letter, one lowercase letter, one number and one special character");
	    }
	}
	
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullName() {
		return first_name + " " + last_name;
	}
	
}

package edu.fra.uas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import java.util.List;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Table;


@Entity // declares this class as an Entity for the database
@Table(name = "User") // creates the table inside the database
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT PRIMARY KEY")
	private long id;

	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(50)")
	private String first_name;
	
	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR(50)")
	private String last_name;

	@Column(name = "USERNAME", columnDefinition = "VARCHAR(50)")
	private String username;

	@Column(name = "PASSWORD", columnDefinition = "VARCHAR(255)")
	private String password;

	@Column(name = "EMAIL", columnDefinition = "VARCHAR(55)")
	private String email;
	
	@OneToMany
	@JoinColumn(name = "PROJECTS")
	private List<Project> projects;

	public User() {
		super();
		projects = new ArrayList<Project>();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean validatePassword(String password) { // requirements have been set for creating a new password
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		if (password.matches(pattern)) {
			return true;
		}
		return false;
	}

	public void setPassword(String password) { // password can be set after the validation has checked that the password
												// matches the requirements
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

package edu.fra.uas.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import java.util.Set;
import java.util.HashSet;


import edu.fra.uas.project.model.Project;
import edu.fra.uas.resource.model.Resource;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;


@Entity // declares this class as an Entity for the database
@Table(name = "User") // creates the table inside the database
public class User {

	// ID
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private long id;
	
	// Attributes

	@Column(name = "FIRST_NAME")
	private String name;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAIL")
	private String email;
	
	// Foreign Keys Collection
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PROJECTS")
	private Set<Project> projects;
	
	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "RESOURCE")
	private Set<Resource> resourceIn;

	public User() {
		super();
		projects = new HashSet<>();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}

package edu.fra.uas.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Project {

	private String name;
	private List<User> members;
	
	private Settings settings;
	
	// Github Test
	

	
	
	public Project () {
		super();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
	
	
	
	
	
}

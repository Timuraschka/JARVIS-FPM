package edu.fra.uas.model;

import java.util.List;

import javax.persistence.Entity;



@Entity
public class Project {

	private String name;
	
	private User ProjectManager;		// who created the project 
	private Settings settings;
	private List<Long> taskLinePosition;	// holds task id's the index represents the lines 
	private List<User> members;				// List of people who can view the Project
	private List<Resource> resources;

	
	
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


	public User getProjectManager() {
		return ProjectManager;
	}


	public void setProjectManager(User projectManager) {
		ProjectManager = projectManager;
	}


	public List<Long> getTaskLinePosition() {
		return taskLinePosition;
	}


	public void setTaskLinePosition(List<Long> taskLinePosition) {
		this.taskLinePosition = taskLinePosition;
	}


	public List<Resource> getResources() {
		return resources;
	}


	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	
	
	
	
	
}

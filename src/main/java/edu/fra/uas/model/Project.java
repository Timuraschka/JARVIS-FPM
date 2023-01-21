package edu.fra.uas.model;

import java.util.List;
import java.util.ArrayList;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;



@Entity
public class Project {
	
	@Id
	@GeneratedValue
	private long id;

	private String name;
	
	private User ProjectManager;				// who created the project & admin
	private Settings settings;
	private List<Long> lineAsIndexToTaskIDs;	// holds task id's the index represents the lines 
	private List<User> members;					// List of people who can view the Project
	private List<Resource> resources;
	private List<String> teams;					

	
	
	public Project () {
		super();
		
		this.settings = new Settings();
		this.lineAsIndexToTaskIDs = new ArrayList<Long>();
		this.members = new ArrayList<User>();
		this.resources = new ArrayList<Resource>();
		this.teams = new ArrayList<String>();
		
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
		return lineAsIndexToTaskIDs;
	}


	public void setTaskLinePosition(List<Long> taskLinePosition) {
		this.lineAsIndexToTaskIDs = taskLinePosition;
	}


	public List<Resource> getResources() {
		return resources;
	}


	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}


	public List<String> getTeams() {
		return teams;
	}


	public void setTeams(List<String> teams) {
		this.teams = teams;
	}
	
	
	
	
	
	
}

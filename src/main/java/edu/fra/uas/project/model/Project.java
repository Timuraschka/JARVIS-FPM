package edu.fra.uas.project.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.settings.model.Settings;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.timetracker.model.Timetracker;

@Entity
@Table(name = "Project")
public class Project {
	
	// ID
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private long id;

	// Attributes
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;
	
	// Collection of Foreign Keys
	
	@OneToMany
	@Column(name = "KEYWORDS")
	private List<String> keywords = new ArrayList<String>();
	
	@OneToMany(cascade = CascadeType.MERGE)
	@Column(name = "TASKS")
	private Set<Task> tasks;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "PROJECT_MANAGER")
	private Set <Resource> projectManager;
	
	@ManyToMany(mappedBy = "project")
	@JoinColumn(name = "RESOURCES")
	private Set<Resource> members;
	
	// Foreign Keys
	
	@OneToOne
	@JoinColumn(name="PROJECT_OWNER")
	private Resource projectOwner;

	@OneToOne(mappedBy = "project")
	@JoinColumn(name = "SETTINGS")
	private Settings settings;

	@JoinColumn(name="TIME")
	private Timetracker time;

	
	
	public Project() {
		
		
		this.settings = new Settings();
		this.members = new HashSet<>();
		this.projectManager =new HashSet<>();
		
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Resource> getMembers() {
		return members;
	}

	public void setMembers(Set<Resource> members) {
		this.members = members;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Set<Resource> getProjectManager() {
		return projectManager;
	}

	public void setProjectManager( Set<Resource> projectManager) {
		this.projectManager = projectManager;
	}

//	public List<Long> getTaskLinePosition() {
//		return lineAsIndexToTaskIDs;
//	}
//
//	public void setTaskLinePosition(List<Long> taskLinePosition) {
//		this.lineAsIndexToTaskIDs = taskLinePosition;
//	}
//
//	public List<Resource> getResources() {
//		return resources;
//	}
//
//	public void setResources(List<Resource> resources) {
//		this.resources = resources;
//	}
//
//	public List<String> getTeams() {
//		return teams;
//	}
//
//	public void setTeams(List<String> teams) {
//		this.teams = teams;
//	}

}

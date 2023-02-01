package edu.fra.uas.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Project")
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT PRIMARY KEY")
	private long id;

	@Column(name = "NAME", columnDefinition = "VARCHAR(50) NOT NULL")
	private String name;

	@Column(name = "DESCRPITION", columnDefinition = "VARCHAR(500)")
	private String description;

	@ManyToOne
	@JoinColumn(name = "PROJECT_MANAGER")
	private User projectManager;

	@ManyToOne
	@JoinColumn(name = "SETTINGS")
	private Settings settings;

	@OneToMany(mappedBy = "project")
	@JoinColumn(name = "RESOURCES")
	private List<Resource> members;
	
	@JoinColumn(name="TIME")
	private Timetracker time;

	public Project() {
		this.settings = new Settings();
		this.members = new ArrayList<>();
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

//	public List<User> getMembers() {
//		return members;
//	}
//
//	public void setMembers(List<User> members) {
//		this.members = members;
//	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public User getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(User projectManager) {
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

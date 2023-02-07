package edu.fra.uas.project.model;


import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


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
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@Column(name = "KEYWORDS")
//	private List<String> keywords = new ArrayList<String>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@Column(name = "TASKS")
	private Set<Task> tasks;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "PROJECT_MANAGER")
	private Set <Resource> projectManager;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<Resource> members;
	
	// Foreign Keys
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PROJECT_OWNER")
	private Resource projectOwner;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "project")
	private Settings settings;

	@OneToOne(fetch = FetchType.EAGER)
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

}

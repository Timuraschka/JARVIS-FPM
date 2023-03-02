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
import edu.fra.uas.task.model.Task;
import edu.fra.uas.timetracker.model.Timetracker;
import java.io.Serializable;

@Entity
@Table(name = "Projects")
public class Project {

	// ID

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private long id;

	// Attributes

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "HOURS_A_DAY")
	private int hours_a_day = 8;

	public int getHours_a_day() {
		return hours_a_day;
	}

	public void setHours_a_day(int hours_a_day) {
		this.hours_a_day = hours_a_day;
	}

	public boolean isAutomatic_shift() {
		return automatic_shift;
	}

	public void setAutomatic_shift(boolean automatic_shift) {
		this.automatic_shift = automatic_shift;
	}

	@Column(name = "AUTOMATIC_SHIFT")
	private boolean automatic_shift = true; // Whether the tasks should shift automatically

	// Collection of Foreign Keys

	// @OneToMany(fetch = FetchType.EAGER)
	// @Column(name = "KEYWORDS")
	// private List<String> keywords = new ArrayList<String>();

	@OneToMany(fetch = FetchType.EAGER)
	@Column(name = "TASKS")
	private Set<Task> tasks;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_MANAGER")
	private Set<Resource> projectManager;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<Resource> members;

	// Foreign Keys

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_OWNER")
	private Resource projectOwner;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TIME")
	private Timetracker time;

	public Project() {

		this.members = new HashSet<>();
		this.projectManager = new HashSet<>();

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

	public Set<Resource> getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Set<Resource> projectManager) {
		this.projectManager = projectManager;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Resource getProjectOwner() {
		return projectOwner;
	}

	public void setProjectOwner(Resource projectOwner) {
		this.projectOwner = projectOwner;
	}

	public Timetracker getTime() {
		return time;
	}

	public void setTime(Timetracker time) {
		this.time = time;
	}

	public long getId() {
		return id;
	}
	
	

}

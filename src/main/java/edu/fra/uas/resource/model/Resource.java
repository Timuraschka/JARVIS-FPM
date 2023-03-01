package edu.fra.uas.resource.model;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fra.uas.JarvisFpmApplication;
import edu.fra.uas.project.model.Project;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.user.model.User;

@Entity // declares this class as an Entity for the database
@Table(name = "Resources") // creates the table inside the database
public class Resource {

	// ID

	@Id // used to identify the columns inside the table
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESOURCE_ID") // creates the column inside the table
	private long id;

	private static final Logger log = LoggerFactory.getLogger(JarvisFpmApplication.class);

	// Attributes

	@Column(name = "NAME") // creates the column inside the table
	private String name;

	@Column(name = "TEAM")
	private String team;

	@Column(name = "HOURLY_RATE")
	private double hourlyRate = 0;

	// Foreign Keys

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SUPERVISOR")
	private Resource supervisor; // usually the Project Manager

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER")
	private User user; // The actual resource

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PROJECT_REFERENCE")
	private Project project;

	// Foreign Keys Collection

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "resources", cascade = CascadeType.ALL)
	private Set<Task> tasks;

	public Resource() {
		super();
		this.tasks = new HashSet<>();

	}

	public void addTaskToRessource(Task task) {
		tasks.add(task);
		log.debug("Task added to the resource " + this.name);
	}

	public void removeTaskFromRessource(Task taskToRemove) {

		for (Task task : tasks) {
			if (task.getId() == taskToRemove.getId()) {
				tasks.remove(task);
				break;
			}
		}
		log.debug("Task removed from resource " + this.name);
	}

	public void setHourlyRate(double hourlyRate) {
		log.debug(name + "'s hourly rate set to : " + hourlyRate);
		this.hourlyRate = hourlyRate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public Resource getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Resource supervisor) {
		this.supervisor = supervisor;
	}

	public User getProjectMember() {
		return user;
	}

	public void setProjectMember(User projectMember) {
		this.user = projectMember;
	}

}

package edu.fra.uas.resource.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fra.uas.JarvisFpmApplication;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.user.model.User;

import javax.persistence.Column;
import javax.persistence.Table;

import java.util.ArrayList;

@Entity // declares this class as an Entity for the database
@Table(name = "Resource") // creates the table inside the database
public class Resource {

	@Id // used to identify the columns inside the table
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT PRIMARY KEY") // creates the column inside the table
	private long id;

	private static final Logger log = LoggerFactory.getLogger(JarvisFpmApplication.class);

	@Column(name = "NAME", columnDefinition = "VARCHAR(50)") // creates the column inside the table
	private String name;

	@Column(name = "TEAM", columnDefinition = "VARCHAR(50)")
	private String team;

	@Column(name = "SUPERVISOR", columnDefinition = "VARCHAR(50)")
	private User supervisor; // usually the Project Manager or #

	@Column(name = "HOURLY_RATE", columnDefinition = "INT")
	private double hourlyRate = 0;
	
	@JoinColumn(name = "PROJECT_MEMBER")
	private User projectMember; // The actual resource

	@OneToMany
	@JoinColumn(name = "TASKS")
	private List<Task> tasks;
	
	
	public Resource() {
		super();
		this.tasks = new ArrayList<Task>();

	}

	public void addTaskToRessource(Task task) {
		tasks.add(task);
		log.debug("Task added to the resource " + this.name);
	}

	public void removeTaskFromRessource(Task task) {
		tasks.remove(task.getLine());
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

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}

	public User getProjectMember() {
		return projectMember;
	}

	public void setProjectMember(User projectMember) {
		this.projectMember = projectMember;
	}

}

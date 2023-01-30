package edu.fra.uas.model;

import java.util.List;

import jakarta.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fra.uas.JarvisFpmApplication;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity // declares this class as an Entity for the database
@Table(name = "Resource") // creates the table inside the database
public class Resource {

	@Id // used to identify the columns inside the table

	private static final Logger log = LoggerFactory.getLogger(JarvisFpmApplication.class);

	@Column(name = "NAME") // creates the column inside the table
	private String name;

	@Column(name = "TEAM")
	private String team;

	@Column(name = "SUPERVISOR")
	private User supervisor; // usually the Project Manager or #

	@Column(name = "PROJECT_MEMBER")
	private User projectMember; // The actual resource

	private List<Task> tasks;

	@Column(name = "HOURLY_RATE")
	private double hourlyRate = 0;

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

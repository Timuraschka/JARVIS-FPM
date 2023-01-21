package edu.fra.uas.model;

import java.util.List;

import javax.persistence.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fra.uas.JarvisFpmApplication;

import java.util.ArrayList;



@Entity
public class Resource {
	
	private static final Logger log = LoggerFactory.getLogger(JarvisFpmApplication.class);
	
	private String name;
	
	private String team;
	
	private User supervisor;			// usually the Project Manager or 
	private User projectMember;			// The actual resource
	private List<Task> tasks;
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
		log.debug(name +"'s hourly rate set to : " + hourlyRate);
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

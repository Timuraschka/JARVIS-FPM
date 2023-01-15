package edu.fra.uas.model;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.fra.uas.JarvisFpmApplication;

/**
 * 
 * @author Timur 
 * 
 * 		This class represents a task with a possible deadline.
 * 
 *         Behaviors of a task: 
 *         1. When the duration gets changed: The End Date shifts depending on the change 
 *         2. One of the predecessor shifts: The task shifts after the predecessor ! If the Setting is not disabled
 */
@Component
public class Task {
	
	private static final Logger log = LoggerFactory.getLogger(JarvisFpmApplication.class);

	private long id;

	// to create a WBS

	private Project project;
	
	private String name;
	private String description;
										// TODO: eine Kommentar Funktion wäre geil für die Member Sicht (Kanban Board)
	
	private Timetracker timetracker;
	private LocalDate deadline;
	private double work_hours;			// this represents the amount of hours the task should need for completion
										// it should change according to the amount of members working on the task

	private List<Task> subtasks; 		// enables the use of tasks as packages TODO: the end of the last subtask should
										// be the end of this task
	private List<Resource> resources;
	private List<Task> dependencies;

	private double cost; 				// total cost of this tasks (resources(sum of hourly rate) * hours of task)
	private boolean critical; 			// part of the critical path?

	private double progress; 			// percentage of completion
	private boolean complete; 			// done?

	private int line; 					// the user can identify the task which are dependencies to this task using the
										// lines of those tasks

	private boolean automatic_shift; // change the Setting for the task individually

	private boolean ready;
	private boolean done = false;

	public Task() {
		super();

		this.automatic_shift = project.getSettings().isAutomatic_shift();

	}

	/**
	 * 
	 * @return whether this task is ready to carry out
	 */
	public boolean isReady() {
		boolean allDone = true;

		if (!dependencies.isEmpty()) {

			for (Task task : dependencies) {

				if (!task.done) {
					allDone = false;
				}
			}
		}

		this.ready = allDone;
		return ready;
	}
	
	/**
	 * This method updates the duration of the task.
	 * It should be called every time the work_hours or the amount of members changes
	 * 
	 * To clarify:
	 * duration: the actual time it takes to finish the task with given resources
	 * work_hours: the amount of hours the tasks needs without considering the resources.
	 * 
	 * 16 work_hours with 2 members equals a duration of 8 hours
	 * 
	 */
	public void updateWorkHours() {
		
		int amountMembers = resources.size();
		if (resources.isEmpty()) {
			log.debug(" >>> Task.java - calculateWorkHoursToDays >>> Resources empty -> work_hours divide by 1 ");
			amountMembers = 1;
		}
		
		this.timetracker.setDuration_in_hours(this.work_hours/amountMembers);
	}
	
	public double getCost() {
		
		double sumHourlyRate = 0;
		
		for (Resource res : resources) {
			sumHourlyRate += res.getHourlyRate();
		}
		
		cost = sumHourlyRate * work_hours;
		
		return cost;
	}
	
	// TODO should this method exist??
	public void setWork_hours(double work_hours) {
		this.work_hours = work_hours;
	}
	
	public double getWork_hours() {
		work_hours = this.timetracker.getDuration_in_hours() * resources.size(); 
		return work_hours;
	}

	

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public List<Task> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<Task> subtasks) {
		this.subtasks = subtasks;
	}

	public boolean isAutomatic_shift() {
		return automatic_shift;
	}

	public void setAutomatic_shift(boolean automatic_shift) {
		this.automatic_shift = automatic_shift;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isCritical() {
		return critical;
	}

	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public long getId() {
		return id;
	}

	public Timetracker getTime() {
		return this.timetracker;
	}

	public void setTime(Timetracker timetracker) {
		this.timetracker = timetracker;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> ressources) {
		this.resources = ressources;
	}

	public List<Task> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Task> predecudor) {
		this.dependencies = predecudor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

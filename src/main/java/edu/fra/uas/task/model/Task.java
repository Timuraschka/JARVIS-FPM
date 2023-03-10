package edu.fra.uas.task.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.timetracker.model.Timetracker;

import jakarta.persistence.Cacheable;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * 
 * 
 * 
 * This class represents a task with a possible deadline.
 * 
 * Behaviors of a task: 1. When the duration gets changed: The End Date shifts
 * depending on the change 2. One of the predecessor shifts: The task shifts
 * after the predecessor ! If the Setting is not disabled
 */
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "Task", include = "non-lazy")

@Entity
@Table(name = "Task")
public class Task {

	private static final Logger log = LoggerFactory.getLogger(Task.class);

	// ID

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")
	private long id;

	// foreign Keys

	public void setId(long id) {
		this.id = id;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}

	@ManyToOne
	@JoinColumn(name = "PROJECT_REFERENCE")
	private Project project;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TIME")
	private Timetracker timetracker;

	@ManyToOne(targetEntity = Task.class)
	@JoinColumn(name = "PARENT_TASK")
	private Task parent;


	// Collection of foreign Keys

	@OneToMany(targetEntity = Task.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SUBTASKS")
	private Set<Task> subtasks = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TASK_RESOURCES", joinColumns = @JoinColumn(name = "TASK_ID"), inverseJoinColumns = @JoinColumn(name = "RESOUCE_ID"))
	private Set<Resource> resources = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "FOLLOWING_TASKS", joinColumns = @JoinColumn(name = "PREREQUISITORS_OF"), inverseJoinColumns = @JoinColumn(name = "TASK_ID"))
	private Set<Task> following_tasks = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PREREQUISITORS_OF", joinColumns = @JoinColumn(name = "FOLLOWING_TASKS"), inverseJoinColumns = @JoinColumn(name = "TASK_ID"))
	private Set<Task> prerequisite_tasks = new HashSet<>();


	// Attributes

	@Column(name = "DEADLINE")
	private LocalDate deadline;

	@Column(name = "WORK_HOURS")
	private double work_hours;

	@Column(name = "LINE_NUMBER")
	private int line;

	@Column(name = "CRITICAL")
	private boolean critical;

	@Column(name = "COST")
	private double cost;

	@Column(name = "PROGRESS")
	private double progress;

	@Column(name = "COMPLETE")
	private boolean complete;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	// @ElementCollection
	// @Column(name = "KEYWORDS")
	// private List<String> keywords = new ArrayList<String>();

	@Column(name = "READY")
	private boolean ready;

	@Column(name = "DONE")
	private boolean done = false;

	@Column(name = "AUTOMATIC_SHIFT")
	private boolean automatic_shift;

	public Task() {

	}


	public void addAsSubTask(Task subtask){
		this.subtasks.add(subtask);
	}

	/**
	 * 
	 * @return whether this task is ready to carry out
	 */
	public boolean isReady() {
		boolean allDone = true;

		if (!following_tasks.isEmpty()) {

			for (Task task : following_tasks) {

				if (!task.done) {
					allDone = false;
				}
			}
		}

		this.ready = allDone;
		return ready;
	}

	/**
	 * This method updates the duration of the task. It should be called every time
	 * the work_hours or the amount of members changes
	 * 
	 * To clarify: duration: the actual time it takes to finish the task with given
	 * resources work_hours: the amount of hours the tasks needs without considering
	 * the resources.
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

		this.timetracker.setDuration_in_hours(this.work_hours / amountMembers);
	}

	public double getCost() {

		double sumHourlyRate = 0;

		for (Resource res : resources) {
			sumHourlyRate += res.getHourlyRate();
		}

		cost = sumHourlyRate * work_hours;

		return cost;
	}

	// public void addKeyword(String keyword) {
	// keywords.add(keyword);
	// }
	//
	// public void deleteKeyword(String keyword) {
	// keywords.remove(keyword);
	// }
	//
	// public void removeAllKeywords() {
	// keywords = new ArrayList<String>();
	// }

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

	public Set<Task> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(Set<Task> subtasks) {
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

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> ressources) {
		this.resources = ressources;
	}

	public Set<Task> getDependencies() {
		return following_tasks;
	}

	public void setDependencies(Set<Task> predecudor) {
		this.following_tasks = predecudor;
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

	public Task getParent() {
		return this.parent;
	}

	public void setParent(Task parent) {
		this.parent = parent;
	}

	public Set<Task> getFollowing_tasks() {
		return following_tasks;
	}

	public void setFollowing_tasks(Set<Task> following_tasks) {
		this.following_tasks = following_tasks;
	}

	public Set<Task> getPrerequisite_tasks() {
		return prerequisite_tasks;
	}

	public void setPrerequisite_tasks(Set<Task> prerequisite_tasks) {
		this.prerequisite_tasks = prerequisite_tasks;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Timetracker getTimetracker() {
		return timetracker;
	}

	public void setTimetracker(Timetracker timetracker) {
		this.timetracker = timetracker;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	// public List<String> getKeywords() {
	// return keywords;
	// }
	//
	// public void setKeywords(List<String> keywords) {
	// this.keywords = keywords;
	// }

}

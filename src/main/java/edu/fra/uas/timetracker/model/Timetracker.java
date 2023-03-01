package edu.fra.uas.timetracker.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.task.model.Task;


/**
 * This class is a simple time tracker. It holds a time period and the duration
 * of it in a form of days and hours.
 * 
 * A change of one of those factors means an adjustment of the others
 * 
 * There could be a library with those things already done
 * 
 * 
 *
 */
@Entity
@Table(name = "Timetracker") // creates the Table in the database
public class Timetracker {

	// ID

	@Id // used to identify the columns inside the table
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TIME_ID")
	private long id;

	// Attributes

	@Column(name = "START_DATE") // creates the Column inside the table
	private LocalDate startDate;

	@Column(name = "END_DATE")
	private LocalDate endDate;

	@Column(name = "DURATION_IN_DAYS")
	private double duration_in_days;

	@Column(name = "DURATION_IN_HOURS")
	private double duration_in_hours;

	// Foreign Keys
	
	@OneToOne(mappedBy = "time", cascade = CascadeType.ALL)
	private Project projectReference;

	@OneToOne(mappedBy = "timetracker", cascade = CascadeType.ALL)
	private Task TaskReference;

	@OneToOne(mappedBy = "time", cascade = CascadeType.ALL)
	private Project project;
	
	
	public Timetracker() {
		super();
	}

	/**
	 * Updates the EndDate according to the duration by using the duration in days
	 * to calculate the new EndDate
	 */
	public void shiftEndDate() {

		int addToStartDate = 0;

		if (duration_in_days % 1 != 0) {
			addToStartDate = (int) (duration_in_days) + 1;
		} else {
			addToStartDate = (int) duration_in_days;
		}

		endDate = startDate.plusDays(addToStartDate);
	}

	/**
	 * sets the duration *in days* to the new duration *in days*, updates the
	 * duration in hours accordingly and shifts the time period
	 * 
	 * @param duration_in_hours
	 */
	public void setDuration_in_days(double duration_in_days) {
		this.duration_in_days = duration_in_days;

		this.duration_in_hours = duration_in_days * projectReference.getSettings().getHours_a_day();
		shiftEndDate();
	}

	public double getDuration_in_hours() {
		return duration_in_hours;
	}

	/**
	 * sets the duration in hours to the new duration in hours, updates the duration
	 * in days and shifts the time period
	 * 
	 * @param duration_in_hours
	 */
	public void setDuration_in_hours(double duration_in_hours) {

		this.duration_in_hours = duration_in_hours;

		this.duration_in_days = duration_in_hours / projectReference.getSettings().getHours_a_day();
		shiftEndDate();
	}

	/**
	 * sets the start date If the end date is already set, then there are 2 Options:
	 * 1. End date shifts according to the duration. 2. The duration gets adjusted
	 * according to the difference between start and end date TODO: decide what you
	 * want, maybe do both and add a option in settings
	 * 
	 * @param startDate
	 */
	public void setStartDate(LocalDate startDate) {

		if (this.startDate == null) {

			this.startDate = startDate;
			return;
		}

		this.startDate = startDate;
		shiftEndDate();

	}

	/**
	 * sets the end Date Now the duration should be adjusted to the difference
	 * between the start and end date
	 * 
	 * @param endDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;

		duration_in_days = startDate.until(endDate, ChronoUnit.DAYS);
		duration_in_hours = duration_in_days * projectReference.getSettings().getHours_a_day();
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public double getDuration_in_days() {
		return duration_in_days;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Project getProjectReference() {
		return projectReference;
	}

	public void setProjectReference(Project projectReference) {
		this.projectReference = projectReference;
	}

	public Task getTaskReference() {
		return TaskReference;
	}

	public void setTaskReference(Task taskReference) {
		TaskReference = taskReference;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


}

package edu.fra.uas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * @author Timur
 * 
 *         To set the Standard Settings for the project.
 */
@Entity // declares this class as an Entity for the database
@Table(name = "Settings") // creates the table inside the database
public class Settings {
	@Id // used to identify the columns inside the table

	@Column(name = "PROJECT") // creates the column inside the table
	private Project project;

	@Column(name = "TIMETRACKER")
	private Timetracker timetracker;

	@Column(name = "HOURS_A_DAY")
	private int hours_a_day = 8;

	@Column(name = "AUTOMATIC_SHIFT")
	private boolean automatic_shift = true; // Whether the tasks should shift automatically

	public Settings() {
		super();
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

}

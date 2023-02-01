package edu.fra.uas.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", columnDefinition = "INT PRIMARY KEY")
	private long id;

	@Column(name = "HOURS_A_DAY", columnDefinition = "INT")
	private int hours_a_day = 8;

	@Column(name = "AUTOMATIC_SHIFT", columnDefinition = "TINYINT")
	private boolean automatic_shift = true; // Whether the tasks should shift automatically

	@JoinColumn(name = "PROJECT") // creates the column inside the table
	private Project project;

	@JoinColumn(name = "TIMETRACKER")
	private Timetracker timetracker;

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

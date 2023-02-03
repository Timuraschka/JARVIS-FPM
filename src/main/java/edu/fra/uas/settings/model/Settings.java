package edu.fra.uas.settings.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.fra.uas.project.model.Project;


/**
 * 
 * 
 *         To set the Standard Settings for the project.
 */
@Entity // declares this class as an Entity for the database
@Table(name = "Settings") // creates the table inside the database
public class Settings {

	@Id // used to identify the columns inside the table
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SETTING_ID")
	private long id;

	@Column(name = "HOURS_A_DAY")
	private int hours_a_day = 8;

	@Column(name = "AUTOMATIC_SHIFT")
	private boolean automatic_shift = true; // Whether the tasks should shift automatically

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROJECT") // creates the column inside the table
	private Project project;

	public Settings() {
		super();
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

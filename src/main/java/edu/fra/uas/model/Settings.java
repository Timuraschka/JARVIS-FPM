package edu.fra.uas.model;

/**
 * 
 * @author timur
 * 
 * To set the Standard Settings for the project.
 */
public class Settings {
	
	long id;
	private Project project;
	private Timetracker timetracker;
	
	private int hours_a_day = 8;
	private boolean automatic_shift = true;		// Whether the tasks should shift automatically
	
	
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
	public long getId() {
		return id;
	}
	
}

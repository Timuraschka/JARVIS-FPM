package edu.fra.uas.project.service.DTO;

import java.util.Date;

public class ProjectDTO {
	
	String titel;
	String leader;
	String team;
	Date deadline;
	String id;
	
	public ProjectDTO(String titel, String leader, String team, Date deadline, String id) {
		super();
		this.titel = titel;
		this.leader = leader;
		this.team = team;
		this.deadline = deadline;
		this.id = id;
	}
	
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	

}

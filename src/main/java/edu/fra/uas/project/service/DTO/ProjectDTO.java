package edu.fra.uas.project.service.DTO;

import java.util.Date;
import org.springframework.hateoas.RepresentationModel;


public class ProjectDTO extends RepresentationModel<ProjectDTO>{

	String titel;
	String leader;
	long leaderID;
	String team;
	long projectID;

	public ProjectDTO(String titel, String leader, String team, Date deadline, long projectID, long leaderID) {
		super();
		this.titel = titel;
		this.leader = leader;
		this.team = team;
		this.projectID = projectID;
		this.leaderID = leaderID;
	}

	public ProjectDTO() {
		// TODO Auto-generated constructor stub
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

	public long getLeaderID() {
		return leaderID;
	}

	public void setLeaderID(long leaderID) {
		this.leaderID = leaderID;
	}

	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}

}

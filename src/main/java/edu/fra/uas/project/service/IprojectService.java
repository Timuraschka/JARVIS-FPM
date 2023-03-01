package edu.fra.uas.project.service;

import java.util.List;


import edu.fra.uas.project.model.Project;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.user.model.User;

public interface IprojectService {


	public Project getProject(long id);
	public List<Project> getAllProjects();
	public List<Resource> getResourcesInProject();
	
	public Project updateProject(Project p, long id_of_project_to_update);
	
	public List<Project> getProjectsForUser(User u);
	
	
	// public Project addProject();	// wie soll das objekt erstellt werden???
	
	
}

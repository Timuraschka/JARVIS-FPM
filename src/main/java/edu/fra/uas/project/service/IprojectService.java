package edu.fra.uas.project.service;

import java.util.List;


import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.service.DTO.ProjectDTO;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.user.model.User;

public interface IprojectService {


	public Project getProject(long id);

	public List<Project> getAllProjects();

	public List<Resource> getResourcesInProject(Project p);
	
	public Project updateProject(Project p, long id_of_project_to_update);
	
	public List<ProjectDTO> getProjectsForUser(User u);
	
	public void changeProject(String ProjectId, Project projectNeu);

	public void deleteProject(String ProjectId);

	public Project addProject(Project p, User user);

	public List<ProjectDTO> convertToDTO(List<Project> projects);
}

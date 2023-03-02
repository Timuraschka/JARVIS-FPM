package edu.fra.uas.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.project.service.DTO.ProjectDTO;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.user.model.User;

@Service
public class ProjectService implements IprojectService {

	@Autowired
	private ProjectRepository projectR;
	
	

	@Override
	public Project getProject(long id) {
		return projectR.findById(id);
	}

	@Override
	public List<Project> getAllProjects() {
		return projectR.findAll();
	}

	@Override
	public List<ProjectDTO> getProjectsForUser(User u){
	
		Set <Project> projects = u.getProjects();
		
		List <ProjectDTO> dtos = new ArrayList();
		
		for (Project p : projects) {
			
			ProjectDTO dto = new ProjectDTO();
			
			dto.setLeader(p.getProjectOwner().getName());
			dto.setTitel(p.getName());
			dto.setProjectID(p.getId());
			dto.setLeaderID(p.getProjectOwner().getProjectMember().getId());
			
			dtos.add(dto);
		}
		
		return null;
	}
	
	
	@Override
	public List<Resource> getResourcesInProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project updateProject(Project p, long id_of_project_to_update) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

package edu.fra.uas.project.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.project.service.DTO.ProjectDTO;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.task.model.Task;
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

	/**
	 * 
	 */
	@Override
	public List<ProjectDTO> getProjectsForUser(User u) {

		Set<Project> projects = u.getProjects();

		List<ProjectDTO> dtos = new ArrayList<>();

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
	public List<Resource> getResourcesInProject(Project p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project updateProject(Project p, long id_of_project_to_update) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * projekt erstellen & die RootTask erstellen & Resource erstellen - User zuweisen, aber logik auch in dem Controller verbaubar
     * @param p
     * @param user
     * @return das Projekt mit der Root task und der Resource, die mit dem User, der das Projekt erstellt hat, verknüpft ist
     */
	@Override
    public Project addProject(Project p, User user){
        

        // : Root Task erstellt und angehängt
        Task root = new Task();
        root.setName("Root");
        Set<Task>tasks = new HashSet<>();
        tasks.add(root);
        p.setTasks(tasks);

        // : Resource erstellt und dem User zugewiesen
        Resource r = new Resource();
        r.setProjectMember(user);
        r.setName(user.getName());

        p.setProjectOwner(r);

        projectR.save(p);
        return p;

    }

	@Override
	public void deleteProject(String ProjectId) {
		Long l = Long.parseLong(ProjectId);
		projectR.delete(getProject(l));

	}

	@Override
	public void changeProject(String ProjectId, Project projectNeu) {
		Long l = Long.parseLong(ProjectId);
		Project projectAlt = getProject(l);
		projectNeu.setId(projectAlt.getId());
		projectR.save(projectNeu);

	}

	@Override
	public List<ProjectDTO> convertToDTO(List<Project> projects) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'convertToDTO'");
	}

}

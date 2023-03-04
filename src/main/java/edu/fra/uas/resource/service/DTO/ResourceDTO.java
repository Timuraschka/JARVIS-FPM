package edu.fra.uas.resource.service.DTO;

import org.springframework.hateoas.RepresentationModel;

import edu.fra.uas.project.service.DTO.ProjectDTO;
import edu.fra.uas.user.service.UserDTO;

public class ResourceDTO extends RepresentationModel<ResourceDTO>{

    private String name;
    private long id;
    private String team;
    private double hourlyRate;
    private ResourceDTO supervisor;
    private UserDTO linkedUser;
    private ProjectDTO linkedProject;

    


    public ResourceDTO(String name, long id, String team, double hourlyRate, ResourceDTO supervisor, UserDTO linkedUser,
            ProjectDTO linkedProject) {
        this.name = name;
        this.id = id;
        this.team = team;
        this.hourlyRate = hourlyRate;
        this.supervisor = supervisor;
        this.linkedUser = linkedUser;
        this.linkedProject = linkedProject;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public double getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public ResourceDTO getSupervisor() {
        return supervisor;
    }
    public void setSupervisor(ResourceDTO supervisor) {
        this.supervisor = supervisor;
    }
    public UserDTO getLinkedUser() {
        return linkedUser;
    }
    public void setLinkedUser(UserDTO linkedUser) {
        this.linkedUser = linkedUser;
    }
    public ProjectDTO getLinkedProject() {
        return linkedProject;
    }
    public void setLinkedProject(ProjectDTO linkedProject) {
        this.linkedProject = linkedProject;
    }
    
    

}

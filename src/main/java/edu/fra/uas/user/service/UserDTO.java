package edu.fra.uas.user.service;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import edu.fra.uas.project.service.DTO.ProjectDTO;
import edu.fra.uas.resource.service.ResourceDTO;

public class UserDTO extends RepresentationModel<UserDTO> {

    private long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Set<ProjectDTO> linkedProjects;
    private Set<ResourceDTO> linkResources;


    public UserDTO(long id, String name, String username, String password, String email, Set<ProjectDTO> linkedProjects,
            Set<ResourceDTO> linkResources) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.linkedProjects = linkedProjects;
        this.linkResources = linkResources;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<ProjectDTO> getLinkedProjects() {
        return linkedProjects;
    }
    public void setLinkedProjects(Set<ProjectDTO> linkedProjects) {
        this.linkedProjects = linkedProjects;
    }
    public Set<ResourceDTO> getLinkResources() {
        return linkResources;
    }
    public void setLinkResources(Set<ResourceDTO> linkResources) {
        this.linkResources = linkResources;
    }



    
}

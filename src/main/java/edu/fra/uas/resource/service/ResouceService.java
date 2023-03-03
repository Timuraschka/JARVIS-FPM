package edu.fra.uas.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.fra.uas.resource.model.Resource;
import java.util.*;
import edu.fra.uas.resource.repository.ResourceRepository;
import edu.fra.uas.resource.service.*;
import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.repository.*;

@Service
public class ResouceService {

    @Autowired
    private ResourceRepository resourceR;
    @Autowired
    private ProjectRepository projectR;

    public List<Resource> getResourceInProject(Project p) {
        List<Resource> resources = new ArrayList<Resource>();

        for (Resource r : resourceR.findAll()) {
            if (r.getProject().getId() == p.getId()) {
                resources.add(r);
            }
        }
        return resources;
    }

    public Resource getResource(String resource_Id) {
        long l = Long.parseLong(resource_Id);
        return resourceR.findById(l).get();
    }

    public void insertResource(Resource resource) {
        resourceR.save(resource);
    }

    public void deleteResource(String ResourceId) {
        resourceR.delete(getResource(ResourceId));
    }

    public void changeResource(String ResourceId, Resource resourceNeu) {
        Resource resourceAlt = getResource(ResourceId);
        resourceAlt.setId(resourceNeu.getId());

        // if (!resourceAlt.getName().equals(resourceNeu.getName())) {
        //     resourceAlt.setName(resourceNeu.getName());
        // }
        // if (resourceAlt.getProject().getId() != resourceNeu.getProject().getId()){
        //     resourceAlt.setProject(resourceNeu.getProject());
    
        // }
        // if (resourceAlt.getHourlyRate() != resourceNeu.getHourlyRate()){
        //     resourceAlt.setHourlyRate(resourceNeu.getHourlyRate());
        // }
        // if(resourceAlt.getProjectMember() != resourceNeu.getProjectMember()){
        //     resourceAlt.setProjectMember(resourceNeu.getProjectMember());
        // }
        // if(resourceAlt.getSupervisor() != resourceNeu.getSupervisor()){
        //     resourceAlt.setSupervisor(resourceNeu.getSupervisor());
        // }
         }
        
    }


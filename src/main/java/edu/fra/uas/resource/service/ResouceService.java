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
        resourceNeu.setId(resourceAlt.getId());
        resourceR.save(resourceNeu);

    }

}

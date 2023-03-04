package edu.fra.uas.resource.service;

import edu.fra.uas.resource.model.Resource;

public interface IresourceService {



    public Resource getResource(String resource_Id);

    public void insertResource(Resource resource);

    public void deleteResource(String ResourceId);

    public void changeResource(String ResourceId, Resource resourceNeu);
}

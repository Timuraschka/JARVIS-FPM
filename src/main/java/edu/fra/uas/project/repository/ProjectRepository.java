package edu.fra.uas.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.user.model.User;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	
	List<Project> findByNameContaining(String name);
    Project findById(long id);
    List<Project> findByProjectManager(User projectManager);
}

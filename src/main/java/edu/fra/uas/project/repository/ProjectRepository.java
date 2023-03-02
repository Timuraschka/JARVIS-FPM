package edu.fra.uas.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.user.model.User;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	
	List<Project> findByNameContaining(String name);
    Project findById(long id);
    List<Project> findByProjectManager(User projectManager);
    
    @Query("SELECT* "
    		+ "FROM Project p"
    		+ "INNER JOIN Rescource r ON p.PROJECT_ID = r.PROJECT_REFERENCE"
    		+ "INNER JOIN User u ON r.USER = u.USER_ID"
    		+ "WHERE u.USER_ID = :userId")
    List<Project> findAllByUser(@Param("userId")long userId);
}

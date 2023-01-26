package RepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import edu.fra.uas.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Project> {

}

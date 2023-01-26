package RepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import edu.fra.uas.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, String> {

}

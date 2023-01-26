package RepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import edu.fra.uas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

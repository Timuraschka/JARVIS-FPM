package edu.fra.uas.user.repository;

import edu.fra.uas.user.model.User;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



	
	
	List<User>findAll();
	User findByName(String name);
	User findById(long ID);

	
	
}

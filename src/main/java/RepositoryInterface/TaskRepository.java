package RepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

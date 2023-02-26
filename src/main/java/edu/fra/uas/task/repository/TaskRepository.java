package edu.fra.uas.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}

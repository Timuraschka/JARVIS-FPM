package edu.fra.uas.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.timetracker.model.Timetracker;

public interface TimetrackerRepository extends JpaRepository<Timetracker, Long>{
	
	Timetracker findById (long id);
	

}

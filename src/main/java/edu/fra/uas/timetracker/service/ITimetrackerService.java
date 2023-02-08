package edu.fra.uas.timetracker.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.fra.uas.timetracker.repository.TimetrackerRepository;

public interface ITimetrackerService {
	
	
	
	public boolean changeByDurationDays(long id, int days);

}

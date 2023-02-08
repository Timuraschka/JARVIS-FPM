package edu.fra.uas.timetracker.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.fra.uas.timetracker.repository.TimetrackerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TimertrackerService {

	@Autowired
	TimetrackerRepository timeRepository;
	
}

package edu.fra.uas.timetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.timetracker.model.Timetracker;
import edu.fra.uas.timetracker.repository.TimetrackerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import edu.fra.uas.timetracker.repository.TimetrackerRepository;
import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.task.repository.TaskRepository;

@Service
public class TimertrackerService {

	@Autowired
	private TimetrackerRepository timeR;

	@Autowired
	private ProjectRepository projectR;

	@Autowired
	private TaskRepository taskR;

	public Timetracker getTimetracker(String timeId) {
		Long l = Long.parseLong(timeId);
		return timeR.findById(l).get();

	}

	public void changeTime(String timeId, Timetracker timeNeu) {
		Timetracker timeAlt = getTimetracker(timeId);
		timeNeu.setId(timeAlt.getId());
		timeR.save(timeNeu);

	}
	public void deleteTime(String timeId){
		timeR.delete(getTimetracker(timeId));
	}
	public void insertTime(Timetracker timetracker){
		timeR.save(timetracker);
	}
}

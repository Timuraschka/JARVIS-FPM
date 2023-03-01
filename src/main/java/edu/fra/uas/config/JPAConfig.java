package edu.fra.uas.config;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.resource.repository.ResourceRepository;
import edu.fra.uas.settings.repository.SettingsRepository;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.task.repository.TaskRepository;
import edu.fra.uas.timetracker.repository.TimetrackerRepository;
import edu.fra.uas.user.model.User;
import edu.fra.uas.user.repository.UserRepository;
import edu.fra.uas.settings.model.Settings;
import edu.fra.uas.timetracker.model.Timetracker;

@Configuration
public class JPAConfig {

	@Bean
	CommandLineRunner init(UserRepository userR, TaskRepository taskR, ProjectRepository projectR,
			ResourceRepository resourceR, SettingsRepository settingsR, TimetrackerRepository timeR) {

		User u1 = new User();
		u1.setEmail("123@gmail.com");
		u1.setPassword("Password123!");

		Project p1 = new Project();
		p1.setName("ProjectTest");

		Task t1 = new Task();
		t1.setName("Aufgabe1");
		t1.setProject(p1);

		Timetracker tt1 = new Timetracker();
		tt1.setTaskReference(t1);

		Settings s1 = new Settings();
		s1.setHours_a_day(10);

		Resource r1 = new Resource();
		r1.setName("Mitarbeiter");

		return args -> {
			userR.save(u1);
			taskR.save(t1);
			projectR.save(p1);
			settingsR.save(s1);
			timeR.save(tt1);
			resourceR.save(r1);
		};

	}

}
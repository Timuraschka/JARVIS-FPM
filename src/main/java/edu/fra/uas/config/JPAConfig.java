package edu.fra.uas.config;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.resource.repository.ResourceRepository;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.task.repository.TaskRepository;
import edu.fra.uas.timetracker.repository.TimetrackerRepository;
import edu.fra.uas.token.Token;
import edu.fra.uas.token.TokenRepository;
import edu.fra.uas.user.model.User;
import edu.fra.uas.user.repository.UserRepository;
import edu.fra.uas.timetracker.model.Timetracker;
import java.util.*;
import java.time.LocalDate;
import edu.fra.uas.resource.service.ResouceService;

@Configuration
public class JPAConfig {

	private static final Logger log = LoggerFactory.getLogger(JPAConfig.class);
	@Bean
	CommandLineRunner init(UserRepository userR, TaskRepository taskR, ProjectRepository projectR,
			ResourceRepository resourceR, TimetrackerRepository timeR, ResouceService resourceS) {

		Project p1 = new Project();
		p1.setName("ProjectTest");
		p1.setDescription("Das Projekt ist das geilste");
		p1.setMembers(null);

		User u1 = new User();
		u1.setEmail("123@gmail.com");
		u1.setPassword("Password123!");
		u1.setId(1);
		u1.setUsername("Quackofif");
		
		Resource r1 = new Resource();
		r1.setProjectMember(u1);
		r1.setProject(p1);
		
		Set<Resource> rx =u1.getResourceIn();
		rx.add(r1);
		u1.setResourceIn(rx);


		// User u2 = new User();
		// u2.setEmail("456@hotmail.de");
		// u2.setPassword("Password456!");
		// u2.setId(2);
		// u2.setUsername("Timuraschka");

		// User u3 = new User();
		// u3.setEmail("lilly@gmx.de");
		// u3.setPassword("Password789!");
		// u3.setId(3);
		// u3.setUsername("Micho mit der Garro");

		// User u4 = new User();
		// u4.setEmail("samuel@gmail.com");
		// u4.setPassword("Testing123!");
		// u4.setId(4);
		// u4.setUsername("Szucki069");

		// Task t1 = new Task();
		// t1.setName("Aufgabe 1");
		// t1.setDescription("Aufbau der Struktur");
		// t1.setProject(p1);
		// t1.setCritical(false);
		// t1.setDone(false);

		// Task t2 = new Task();
		// t2.setName("Aufgabe 2");
		// t2.setProject(p1);
		// t2.setParent(t1);

		// Task t3 = new Task();
		// t3.setName("Aufgabe 3");
		// t3.setProject(p1);
		// t3.setParent(t1);

		// Timetracker tt1 = new Timetracker();
		// tt1.setProject(p1);
		// tt1.setTaskReference(t1);
	

		Token t1 = new Token(u1.getId());
		TokenRepository tR = new TokenRepository();
		tR.tokenList.add(t1);

		
		return args -> {
			// projectR.save(p1);
			userR.save(u1);
			log.info(">>>>>>>   "+t1.getUserID()+"  "+t1.getToken());
			// userR.save(u2);
			// userR.save(u3);
			// userR.save(u4);
			// taskR.save(t1);
			// taskR.save(t2);
			// taskR.save(t3);
			// timeR.save(tt1);
			
		};

	}

}
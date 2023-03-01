package edu.fra.uas.config;

import java.util.HashSet;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.resource.repository.ResourceRepository;
import edu.fra.uas.settings.repository.SettingsRepository;
import edu.fra.uas.task.repository.TaskRepository;
import edu.fra.uas.timetracker.repository.TimetrackerRepository;
import edu.fra.uas.user.model.User;
import edu.fra.uas.user.repository.UserRepository;



@Configuration
public class JPAConfig {


	@Bean
	CommandLineRunner init (UserRepository userR, TaskRepository taskR, ProjectRepository projectR, ResourceRepository resourceR, SettingsRepository settingsR, TimetrackerRepository timeR) {
		
		User u1 = new User();
		u1.setEmail("123@gmail.com");
		u1.setPassword("Password123!");
		
		
		

		
		return args -> {
			userR.save(u1);
			
		};
		
	}

}
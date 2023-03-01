package edu.fra.uas.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.controller.ApiController;
import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.service.ProjectService;
import edu.fra.uas.resource.service.ResouceService;
import edu.fra.uas.settings.service.SettingsService;
import edu.fra.uas.task.service.TaskService;
import edu.fra.uas.timetracker.service.TimertrackerService;
import edu.fra.uas.user.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
	

	private static final Logger log = LoggerFactory.getLogger(ApiController.class);
	
	private UserService userS;
	private TaskService taskS;
	private ProjectService projectS;
	private ResouceService resourceS;
	private SettingsService settingsS;
	private TimertrackerService timeS;
	
	
	@Autowired
	public ApiController (UserService userS, TaskService taskS, ProjectService projectS, 
			ResouceService resourceS, SettingsService settingsS, TimertrackerService timeS) 
	{
		
		this.userS = userS;
		this.taskS = taskS;
		this.projectS = projectS;
		this.resourceS = resourceS;
		this.settingsS = settingsS;
		this.timeS = timeS;
		
	}
	
	/**
	 * Diese Methode braucht 
	 */
	@RequestMapping( value = "user/{token}/project",
			method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getProjects (@PathVariable("token") String token){
		
//		List<Project> = projectS.getAllProjects();
		
		return null;
	}
}

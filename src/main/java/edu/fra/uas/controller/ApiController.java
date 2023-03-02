package edu.fra.uas.controller;


import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.fra.uas.controller.ApiController;
import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.service.ProjectService;
import edu.fra.uas.project.service.DTO.ProjectDTO;
import edu.fra.uas.resource.service.ResouceService;
import edu.fra.uas.settings.service.SettingsService;
import edu.fra.uas.task.service.TaskService;
import edu.fra.uas.timetracker.service.TimertrackerService;
import edu.fra.uas.token.TokenService;
import edu.fra.uas.user.model.User;
import edu.fra.uas.user.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
	

	private static final Logger log = LoggerFactory.getLogger(ApiController.class);
	
	private UserService userS;
	private TokenService tokenS;
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
	 * 
	 */
	@RequestMapping( value = "user/{token}/project",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getProjects (@PathVariable("token") String token){
		
		User u = userS.getUserWithToken(token);
		List<ProjectDTO> projects = projectS.getProjectsForUser(u);
		
		Collection<ProjectDTO> collection = projects;
		return new ResponseEntity<>(collection, HttpStatus.OK);
	}
	
	
}








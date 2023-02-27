package edu.fra.uas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.controller.ApiController;
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
	
	private UserService userR;
	private TaskService taskR;
	private ProjectService projectR;
	private ResouceService resourceR;
	private SettingsService settingsR;
	private TimertrackerService timeR;
	
	
	@Autowired
	public ApiController (UserService userR, TaskService taskR, ProjectService projectR, 
			ResouceService resourceR, SettingsService settingsR, TimertrackerService timeR) 
	{
		
		this.userR = userR;
		this.taskR = taskR;
		this.projectR = projectR;
		this.resourceR = resourceR;
		this.settingsR = settingsR;
		this.timeR = timeR;
		
	}
	
	@RequestMapping( value = "",
			method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CollectionModel>
}

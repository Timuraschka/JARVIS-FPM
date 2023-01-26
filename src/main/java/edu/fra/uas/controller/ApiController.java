package edu.fra.uas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.JarvisFpmApplication;
import edu.fra.uas.service.*;

@RestController
@RequestMapping("/api")
public class ApiController {

	private static final Logger log = LoggerFactory.getLogger(JarvisFpmApplication.class);

	@Autowired
	private TaskService taskService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private SettingsService settingService;
	@Autowired
	private UserService userService;
	
	
}
//d

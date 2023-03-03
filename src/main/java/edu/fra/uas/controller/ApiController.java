package edu.fra.uas.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import edu.fra.uas.task.service.TaskService;
import edu.fra.uas.task.service.DTO.TaskDTO;
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
	private TimertrackerService timeS;

	@Autowired
	public ApiController(UserService userS, TaskService taskS, ProjectService projectS,
			ResouceService resourceS, TimertrackerService timeS) {

		this.userS = userS;
		this.taskS = taskS;
		this.projectS = projectS;
		this.resourceS = resourceS;
		this.timeS = timeS;

	}

	@GetMapping
	public ResponseEntity<String> getLogin() {
		return new ResponseEntity<String>("login", HttpStatusCode.valueOf(200));
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/user/{user_id}/{token}/project", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> getProjects(@PathVariable("user_id") long user_id,
			@PathVariable("token") String token) {

		User u = userS.getUserWithToken(token);
		List<ProjectDTO> projects = projectS.getProjectsForUser(u);

		List<ProjectDTO> collection = projects;
		return new ResponseEntity<List<ProjectDTO>>(collection, HttpStatusCode.valueOf(200));
	}

	@RequestMapping(value = "/user/{token}/project/{project_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getKanbanWBS(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id) {
		List<TaskDTO> kanbanWBS = taskS.getTasksInProject(projectS.getProject(project_id));
		List<TaskDTO> collection = kanbanWBS;
		return new ResponseEntity<List<TaskDTO>>(collection, HttpStatusCode.valueOf(200));
	}

	@RequestMapping(value = "/user/{token}/project/{project_id}/tasks/{task_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> getKanbanWBSTask(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("task_id") long task_id) {

		return null;
	}

	@RequestMapping(value = "/user/{token}/project/{project_id}/tasks/{task_id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> putKanbanWBStask(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("task_id") long task_id) {

		return null;
	}

	@RequestMapping(value = "/user/{token}/project/{project_id}/tasks/{task_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> postWBSTask(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("task_id") long task_id) {
		return null;
	}

	@RequestMapping(value = "/user/{token}/project/{project_id}/resource", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> getResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id) {
		return null;
	}

	@RequestMapping(value = "/user/{token}/project/{project_id}/resource/{resource_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> postResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("resource_id") long resource_id) {
		return null;
	}

	@RequestMapping(value = "/user/{token}/project/{project_id}/resource/{resource_id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> putResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("resource_id") long resource_id) {
		return null;
	}

}

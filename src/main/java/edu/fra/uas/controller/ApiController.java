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
import edu.fra.uas.resource.service.ResourceDTO;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.task.service.TaskService;
import edu.fra.uas.task.service.DTO.TaskDTO;
import edu.fra.uas.timetracker.service.TimertrackerService;
import edu.fra.uas.token.TokenService;
import edu.fra.uas.user.model.User;
import edu.fra.uas.user.service.UserService;

/**
 * This is the main Rest Controller class.
 * 
 */
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

	/**
	 * getLogin() gets acivated for the URI: "/api"
	 * it should return the Login.html (at least in a usual Controller not a REST
	 * Controller)
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<String> getLogin() {
		log.info("LOGIN");
		return new ResponseEntity<String>("login", HttpStatusCode.valueOf(200));
	}

	/**
	 * This method gets called when the user has logged in.
	 * It returns a List of all Projects, which the current user is any kind of member in.
	 */
	@RequestMapping(value = "/api/user/{token}/project", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> getProjects(@PathVariable("user_id") long user_id,
			@PathVariable("token") String token) {

		User u = userS.getUserWithToken(token);
		List<ProjectDTO> projects = projectS.getProjectsForUser(u);

		List<ProjectDTO> collection = projects;
		return new ResponseEntity<List<ProjectDTO>>(collection, HttpStatusCode.valueOf(200));
	}

	/**
	 * This mehtod is called after the user chose one project.
	 * It returns every Task in the project, so those can be displayed in the Kanban Board and the WBS.
	 * Both use the same data.
	 * @param token
	 * @param project_id
	 * @return List<TaskDTO>
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getAllTasksOfOneProject(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id) 
	{
		List<Task> kanbanWBS = taskS.getTasksInProject(projectS.getProject(project_id));
		List<TaskDTO> collection = taskS.convertToDTO(kanbanWBS);
		return new ResponseEntity<List<TaskDTO>>(collection, HttpStatusCode.valueOf(200));
	}

	/**
	 * This method is called when the User picks a task represented in the Kanban Board or the WBS
	 * This method should returns the Task details of a single Task. It gets the ID of the Task out of the URI
	 * @param token
	 * @param project_id
	 * @param task_id
	 * @return TaskDTO
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/tasks/{task_id}", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> getTask(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("task_id") long task_id) {

		return null;
	}

	/**
	 * This method is called after the User changed any value of a displayed Task and sumbits the changes.
	 * The method should update the changed task in the database.
	 * It returns the updated List of TaskDTO's.
	 * @param token
	 * @param project_id
	 * @param task_id
	 * @return updated List of TaskDTO's.
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/tasks/{task_id}", 
					method = RequestMethod.PUT, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> updateTask(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("task_id") long task_id) {

		return null;
	}

	/**
	 * This method is called when the user creates a Task in the WBS and submits it.
	 * It adds the Task to the Task List in the Project 
	 * if the user added Resources to the Task, then the Task is also added to the task list in Resource and the cost of the task can be calculated
	 * 
	 * The method returns a updated list of TaskDTO's
	 * @param token
	 * @param project_id
	 * @param task_id
	 * @return updated list of TaskDTO's
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/tasks/{task_id}", 
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> postWBSTask(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("task_id") long task_id) {
		return null;
	}

	/**
	 * This method is called whenever the FrontEnd needs all resources assigned to the given project.
	 * It returns all resources assigned to the given project
	 * @param token
	 * @param project_id
	 * @return all resources assigned to the given project
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/resource", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResourceDTO>> getResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id) {
		return null;
	}

	/**
	 * This method is called when the project owner creates a new resource to the given project
	 * It creates a Resource object with the given data (from the http request) and links it to the given project
	 * This gets saved and returns a upadted Link of ResourceDTO's 
	 * @param token
	 * @param project_id
	 * @return upadted Link of ResourceDTO's 
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/resource", 
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> postResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id) {
		return null;
	}

	/**
	 * This mehtod gets called when the project owner changes the values of a resource.
	 * It updates the given resource in the database and returns a updated list of ResourceDTO's
	 * @param token
	 * @param project_id
	 * @param resource_id
	 * @return updated list of ResourceDTO's
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/resource/{resource_id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResourceDTO>> putResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("resource_id") long resource_id) {
		return null;
	}

}

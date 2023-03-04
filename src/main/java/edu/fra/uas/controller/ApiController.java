package edu.fra.uas.controller;

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
import org.springframework.web.bind.annotation.RestController;

import edu.fra.uas.controller.ApiController;
import edu.fra.uas.project.service.ProjectService;
import edu.fra.uas.project.service.DTO.ProjectDTO;
import edu.fra.uas.resource.service.ResouceService;
import edu.fra.uas.resource.service.DTO.ResourceDTO;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.task.service.TaskService;
import edu.fra.uas.task.service.DTO.TaskDTO;
import edu.fra.uas.timetracker.service.TimertrackerService;
import edu.fra.uas.token.model.Token;
import edu.fra.uas.token.service.TokenService;
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

	private UserService userService;
	private TokenService tokenService;
	private TaskService taskService;
	private ProjectService projectS;
	private ResouceService resourceService;
	private TimertrackerService timeService;

	@Autowired
	public ApiController(UserService userS, TaskService taskS, ProjectService projectS,
			ResouceService resourceS, TimertrackerService timeS) {

		this.userService = userS;
		this.taskService = taskS;
		this.projectS = projectS;
		this.resourceService = resourceS;
		this.timeService = timeS;

	}

	/**
	 * This mehtod gets acivated by the URI: "/api"
	 * It should return the content to display at the home.html
	 * The Home html is a introduction site to any visitor of the website, who is not logged in.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<String> openHome() {
		log.info("Home.html");
		return null;
	}


	/*############################################################# */
	// SESSION METHODS:




	 /**
     * This method checks the input of the user 
	 * if the input is correct it creates the token for the the session, with which the user can access
	 * It returns a HTTP Status from which the AddProjects.html can be accessed
     * @param user
     * @return HTTP Status
     */
    @RequestMapping(value = "/api/login",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody User user) {

        if (userService.loginUser(user.getEmail(), user.getPassword())){

            Token token = tokenService.createToken(userService.getUserWithEmail(user.getEmail()).getId());
            System.out.println(userService.getUserWithEmail(user.getEmail()).getId());
            System.out.println(token.getToken().toString());
            return ResponseEntity.status(HttpStatus.OK).body(token.getToken());

        }


        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }


	/**
	 * This method retruns the UserID of the current User
	 * @param token
	 * @return
	 */
    @RequestMapping(value = "/api/users/{token}", method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("token")  UUID token ) {

        if (token!=null){

            if (tokenService.checkIfTokenExistsAndIsValid(token)) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(tokenService.getUserID(token).getUserID()));
            }
        }

        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }


	/**
	 * This Method logs the current user out. 
	 * It deletes the token of the User and removes the access with this.
	 * @param token
	 * @return HTTP Staus
	 */
    @RequestMapping(value = "/api/users/{token}/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(@PathVariable("token") UUID token) {

        if (tokenService.checkIfTokenExistsAndIsValid(token)) {
            tokenService.deleteToken(token);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }









	/*############################################################################## */
	// PROJECT RELATED METHODS (all accessed from Projects.html)



	/**
	 * This method gets called when the user has logged in.
	 * It returns a List of all Projects, which the current user is any kind of member in.
	 * @param token
	 * @return  List<ProjectDTO>
	 */
	@RequestMapping(value = "/api/users/{token}/projects", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> getProjects(@PathVariable("token") String token) {

		User u = userService.getUserWithToken(token);
		List<ProjectDTO> projects = projectS.getProjectsForUser(u);

		List<ProjectDTO> collection = projects;
		return new ResponseEntity<List<ProjectDTO>>(collection, HttpStatusCode.valueOf(200));
	}


	/**
	 * This method should use the given JSON to create a Project Object and sets its project owner to the current user
	 * It creates a Resource in this project and links it to the current User
	 * It also adds the created Project to the User's list of projects
	 * It returns a list of all projects, which the current user is any kind of member in
	 * @param token
	 * @returnlist List<ProjectDTO>
	 */
	@RequestMapping(value = "/api/users/{token}/projects", 
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> createProject(@PathVariable("token") String token) {

		return null;
	}

	/**
	 * This method should map the User who opend the Project with given project id to it's Resource in this project
	 * and decide which view the User gets first
	 * if the User is a project owner he gets the WBS view
	 * if the User is a normal Teammember he gets the Kaban view
	 * @param token
	 * @param project_id
	 * @return	String ("WBS" or "Kanban")
	 */
	@RequestMapping(value = "/api/users/{token}/projects/{project_id}", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> openProjects(@PathVariable("token") String token, @PathVariable("project_id") long project_id) {

		return null;
	}

	/**
	 * This method changes the details of the given project (titel, description etc.)
	 * But first it checks wether the current user is the owner of the project.
	 * Afterwards it updates the project in the database
	 * and returns a updatet list of projects, which the current user is any kind of member in
	 * @param token
	 * @param project_id
	 * @return updated List<ProjectDTO>
	 */
	@RequestMapping(value = "/api/users/{token}/projects/{project_id}", 
					method = RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> changeProjectDetails(@PathVariable("token") String token, @PathVariable("project_id") long project_id) {

		return null;
	}

	/**
	 * If the User with given token is the porject owner of thr project with given project id,
	 * then the method deletes the project from the database.
	 * If the User is just a Project Member the Method removes this project from the User's list of projects
	 * @param token
	 * @param project_id
	 * @return updated List<ProjectDTO>
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}", 
					method = RequestMethod.DELETE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> deleteProject(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id) 
	{
		return null;
	}







	/*################################################################## */
	// TASK METHODDS


	/**
	 * This method maps tasks to the project with the given Project Id
	 * returns a list of Tasks
	 * @param token
	 * @param project_id
	 * @return List<TaskDTO>
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/tasks", 
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getTasksInProject(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id) 
	{
		List<Task> kanbanWBS = taskService.getTasksInProject(projectS.getProject(project_id));
		List<TaskDTO> collection = taskService.convertToDTO(kanbanWBS);
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
	public ResponseEntity<List<TaskDTO>> getTask(@PathVariable("token") String token,
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
	 * @return updated List<TaskDTO>
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
	 * @return updated List<TaskDTO>
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/tasks/{task_id}", 
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> createTask(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("task_id") long task_id) {
		return null;
	}




	/*############################################################################ */
	// RESOURCE METHODS



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
	public ResponseEntity<List<ResourceDTO>> getAllResources(@PathVariable("token") String token,
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
	public ResponseEntity<List> addNewResource(@PathVariable("token") String token,
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
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/resource/{resource_id}", 
					method = RequestMethod.PUT, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResourceDTO>> updateResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("resource_id") long resource_id) {
		return null;
	}

	/**
	 * This method should delete the given Resource.
	 * To do this is also deletes the Resource in the User's Resource list(Set)
	 * and in the Projects Resource list(Set).
	 * It returns the updated lsit of ResourceDTO'S
	 * @param token
	 * @param project_id
	 * @param resource_id
	 * @return updated lsit of ResourceDTO'S
	 */
	@RequestMapping(value = "/api/user/{token}/project/{project_id}/resource/{resource_id}", 
					method = RequestMethod.DELETE, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResourceDTO>> deleteResource(@PathVariable("token") String token,
			@PathVariable("project_id") long project_id, @PathVariable("resource_id") long resource_id) {
		return null;
	}

}

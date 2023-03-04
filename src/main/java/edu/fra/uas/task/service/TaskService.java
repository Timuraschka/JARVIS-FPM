package edu.fra.uas.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.mapping.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.controller.ApiController;
import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.task.repository.TaskRepository;
import edu.fra.uas.task.service.DTO.TaskDTO;
import edu.fra.uas.timetracker.model.Timetracker;

@Service
public class TaskService implements ITaskService {

	private static final Logger log = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskR;
	@Autowired
	private ProjectRepository projectR;

	/**
	 * This Method maps the tasks of the given project to a line number
	 * 
	 * @param Project
	 */
	@Override
	public TreeMap<Integer, Task> getTaskHierarchy(Project p) {

		// get the first task
		Task root;
		root = getRootTaskOfProject(p);

		TreeMap<Integer, Task> tree = new TreeMap<>();
		tree = addTaskToMap(root, tree, 0);

		return tree;
	}

	private Task getRootTaskOfProject(Project p) {
		List<Task> tasks = getTasksInProject(p);
		for (Task t : tasks) {
			if (t.getParent() == null) {
				// ist die "RootTask"
				return t;
			}
		}
		log.debug("TASK SERVICE: Root Task konnte nicht gefunden werden alle tasks haben einen parent!!");
		return null;
	}

	private TreeMap<Integer, Task> addTaskToMap(Task task, TreeMap<Integer, Task> tasksMap) {
		int lineNumber = tasksMap.size() +1;
		tasksMap.put(lineNumber, task);

		if (task.getSubtasks() != null) {
			for (Task subtask : task.getSubtasks()) {
				addTaskToMap(subtask, tasksMap);
			}
		}

		return tasksMap;
	}

	public List<Task> getTasksInProject(Project p) {
		List<Task> tasks = new ArrayList<Task>();

		for (Task t : taskR.findAll()) {
			if (t.getProject().getId() == p.getId()) {
				if (t.getParent() == null) {
					// is the RootTask
					break;
				}
				tasks.add(t);
			}
		}

		return tasks;
	}

	public List<TaskDTO> convertToDTO(List<Task> taskList){
		List<TaskDTO> listDTO = new ArrayList<>();
		for(Task t : taskList){
			TaskDTO dto = new TaskDTO(t.getLine(), t.getName(), t.getTime().getStartDate(), t.getTime().getEndDate(), t.getResources(), t.getPrerequisite_tasks(), t.getCost());
			listDTO.add(dto);
		}
		return listDTO;
	}

	/**
	 * This method sets the parent of the task to the root task of the project the given task is linked to
	 * then it sets the line number and saves the task in the database
	 * @param task

	 */
	public void addTask(Task task) {

		if (task.getParent() == null) {
			task.setParent(getRootTaskOfProject(task.getProject()));
		}

		if (!task.getSubtasks().isEmpty()) {
			for (Task t : task.getSubtasks()) {
				t.setParent(task);
			}
		}

		addTaskToMap(task,getTaskHierarchy(task.getProject()));

		task.setLine(getTaskHierarchy(task.getProject()).size());

		taskR.save(task);
	}

	public void changeTask(String TaskId, Task taskNeu) {
		Long l = Long.parseLong(TaskId);
		Task taskAlt = taskR.findById(l).get();
		taskNeu.setId(taskAlt.getId());
		taskR.save(taskNeu);

	}

	public Timetracker getTime(String TaskId) {
		Long l = Long.parseLong(TaskId);
		return taskR.findById(l).get().getTime();
	}

}

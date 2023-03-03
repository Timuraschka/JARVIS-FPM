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

@Service
public class TaskService implements ITaskService {

	private static final Logger log = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskR;
	@Autowired
	private ProjectRepository projectR;

	/**
	 * Die TreeMap repäsentiert die Bezeihung zwischen der "line"
	 * und der Task.
	 * 
	 * Root Task -> line 0
	 * 
	 * 
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

	private TreeMap<Integer, Task> addTaskToMap(Task task, TreeMap<Integer, Task> tasksMap, int lineNumber) {
		tasksMap.put(lineNumber, task);

		if (task.getSubtasks() != null) {
			for (Task subtask : task.getSubtasks()) {
				addTaskToMap(subtask, tasksMap, lineNumber + 1);
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

	/**
	 * 
	 * @param t - is the task which should be added to the database
	 * 
	 *          Die RootTask wird nicht über diese Methode laufen das passier in der
	 *          createProject Methode im ProjectService
	 * @return
	 */
	public boolean addTask(Task t) {

		if (t.getParent() == null) {
			t.setParent(getRootTaskOfProject(t.getProject()));
		}

		if (!t.getSubtasks().isEmpty()) {
			for (Task i : t.getSubtasks()) {
				i.setParent(t);
			}
		}

		t.setLine(t.getProject().getTasks().size());
		return true;
	}

	public void changeTask(String TaskId, Task taskNeu) {
		Task taskAlt = getTask(TaskId);
		taskNeu.setId(taskAlt.getId());
		taskR.save(taskNeu);

	}

	public Task getTask(String TaskId) {
		Long l = Long.parseLong(TaskId);
		return taskR.findById(l).get();
	}

}

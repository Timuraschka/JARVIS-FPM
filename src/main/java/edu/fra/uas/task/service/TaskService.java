package edu.fra.uas.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;

import edu.fra.uas.project.model.Project;
import edu.fra.uas.project.repository.ProjectRepository;
import edu.fra.uas.task.model.RootTask;
import edu.fra.uas.task.model.Task;
import edu.fra.uas.task.repository.TaskRepository;

public class TaskService implements ITaskService{

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
		Task root = p.getRootTask();
		
		TreeMap<Integer, Task> tree = new TreeMap<>();
		tree = addTaskToMap(root, tree, 0);
		
		return tree;
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
			if (t.getProject().getId() == p.getId() ) {
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
	 * Die RootTask wird nicht über diese Methode laufen das passier in der createProject Methode im ProjectService
	 * @return
	 */
	public boolean addTask (Task t) {
		
		if (t.getParent() == null) {
			t.setParent(t.getProject().getRootTask());
		}
		
		if ( ! t.getSubtasks().isEmpty()) {
			for (Task i : t.getSubtasks()) {
				i.setParent(t);
			}
		}
		
		t.setLine(t.getProject().getTasks().size());
		return true;
	}
	


}

package edu.fra.uas.task.service;


import java.util.TreeMap;


import edu.fra.uas.project.model.Project;
import edu.fra.uas.task.model.Task;

public interface ITaskService {
	
	


	TreeMap<Integer, Task> getTaskHierarchy(Project p);

}

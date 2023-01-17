package edu.fra.uas.service;

import java.util.List;

import edu.fra.uas.model.Task;

public interface TaskService {

	
	public void setName(String name);
	public void setDescription(String decription);	
	public void addKeyword(String keyword);
	public void removeKeyword(String keyword); 
	
	public void setStartDate(String startDate);
	public void setDurationInDays(String duration);
	public void setEndDate(String endDate); 
	public void setDeadLine(String deadline);
	
	public void addSubtask(String lineOfTop, String lineOfTaskToSub);
	public void removeSubtask(String taskToRemoveLine, String taskTop);
	
	public List<Task> getAllTasks();
	
	public List<Task> filterForResource(List<Task> tasksToFilter,String id);
	public List<Task> filterForMultipleResources(List<Task> tasksToFilter, List<String> ids);
	
	public List<Task> filterForKeyword(List<Task> tasksToFilter,String username);
	public List<Task> filterForMultipleKeywords(List<Task> tasksToFilter, List<String> keywords);
	// maybe i need only the filter for multiple methods
	
	public List<Task> getCriticalTasks(List<Task> tasksToFilter);
	public List<Task> getReadyTasks(List<Task> tasksToFilter);
	public List<Task> getTasksInProgress(List<Task> tasksToFilter);
	public List<Task> getDoneTasks(List<Task> tasksToFilter);
	// these checks/filters could be done in java script
	
	
}
package edu.fra.uas.task.service.DTO;

import java.time.LocalDate;
import java.util.*;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;
import edu.fra.uas.resource.model.Resource;
import edu.fra.uas.task.model.Task;

public class TaskDTO extends RepresentationModel<TaskDTO> {

    int line;
    String title;
    LocalDate startDate;
    LocalDate endDate;
    Set<Resource> resource;
    Set<Task> prerequisiteTask;
    double cost;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartLocalDate() {
        return startDate;
    }

    public void setStartLocalDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndLocalDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Resource> getResource() {
        return resource;
    }

    public void setResource(Set<Resource> resource) {
        this.resource = resource;
    }

    public Set<Task> getPrerequisiteTask() {
        return prerequisiteTask;
    }

    public void setPrerequisiteTask(Set<Task> prerequisiteTask) {
        this.prerequisiteTask = prerequisiteTask;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public TaskDTO(int line, String title, LocalDate startDate, LocalDate endDate, Set<Resource> resource, Set<Task> prerequisiteTask,
            double cost) {
        super();
        this.line = line;
        this.title = title;
        this.startDate = startDate;
        this.resource = resource;
        this.prerequisiteTask = prerequisiteTask;
        this.cost = cost;

    }
}

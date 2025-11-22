package com.basnet.todoapp.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.basnet.todoapp.entities.Task;
// import com.basnet.todoapp.exception.ResourceNotFoundException;
import com.basnet.todoapp.service.TaskService;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/create")
    public Task  createTask(@RequestBody Task task){
        return taskService.saveTask(task);
 
    }
    @GetMapping("/task")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }
     @GetMapping("/task/{id}")
    public Task getTaskById( @PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Long id , @RequestBody Task updatedTask){
        return taskService.updateTask(id,updatedTask);
    }
    @PatchMapping("/update/{id}")
    public Task patchTask(@PathVariable Long id , @RequestBody Task updatedTask){
        return taskService.updateTask(id,updatedTask);
    }
    @DeleteMapping("/delete/{id}")
    public Task deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }
    @GetMapping("/task/uncompleted")
    public List<Task> getUncompletedTasks(){
        return taskService.getUncompletedTasks();
    }
     @GetMapping("/task/completed")
    public List<Task> getCompletedTasks(){
        return taskService.getCompletedTasks();
    }

}

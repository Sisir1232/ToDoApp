package com.basnet.todoapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.basnet.todoapp.entities.Task;
import com.basnet.todoapp.service.TaskService;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/create")
    public Task  createTask(@RequestBody Task task){
        return taskService.saveTask(task);
 
    }

}

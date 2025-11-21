package com.basnet.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basnet.todoapp.entities.Task;
import com.basnet.todoapp.exception.ResourceNotFoundException;
import com.basnet.todoapp.repo.TaskRepo;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;
	public Task  saveTask(Task task) {
		return taskRepo.save(task);
	}
    public List<Task> getTasks() {
       return  taskRepo.findAll();
    }
    public Task getTaskById(Long id) {
       return  taskRepo.findById(id)
                      .orElseThrow(()->new ResourceNotFoundException("not found with id "+ id));
    }

}

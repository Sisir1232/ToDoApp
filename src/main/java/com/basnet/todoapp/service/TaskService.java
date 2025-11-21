package com.basnet.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basnet.todoapp.entities.Task;
import com.basnet.todoapp.repo.TaskRepo;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;
	public Task  saveTask(Task task) {
		return taskRepo.save(task);
	}

}

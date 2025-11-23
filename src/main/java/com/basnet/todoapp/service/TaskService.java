package com.basnet.todoapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Task> getTasks(Pageable pageable) {
       return  taskRepo.findAll(pageable);
    }
    public Task getTaskById(Long id) {
       return  taskRepo.findById(id)
                      .orElseThrow(()->new ResourceNotFoundException("not found with id "+ id));
    }
    public Task updateTask(Long id, Task updatedTask) {
        Task utask=taskRepo.findById(id)
                           .orElseThrow(()->new ResourceNotFoundException("user not found by id"+id));
        utask.setId(id);
        utask.setTitle(updatedTask.getTitle());
        utask.setDescription(updatedTask.getDescription());
        utask.setCompleted(updatedTask.isCompleted());
        taskRepo.save(utask);
        return utask;

    }
    public Task patchTask(Long id, Task updatedTask) {
        Task utask=taskRepo.findById(id)
                           .orElseThrow(()->new ResourceNotFoundException("user not found by id"+id));
        utask.setId(id);
        if(updatedTask.getTitle()!=null){
            utask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getDescription()!=null){
            utask.setTitle(updatedTask.getDescription());
        }
        if(updatedTask.getDescription()!=null){
            utask.setTitle(updatedTask.getDescription());
        }
        if(updatedTask.isCompleted()){
            utask.setCompleted(true);
        }
        
        taskRepo.save(utask);
        return utask;

    }
    public Task deleteTask(Long id) {
        taskRepo.deleteById(id);
        return taskRepo.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("user not found by id"+id));
    }
    public List<Task> getUncompletedTasks() {
        return taskRepo.findByCompletedFalse();
    }
    public List<Task> getCompletedTasks() {
        return taskRepo.findByCompletedTrue();
    }
    public List<Task> getTaskByDate(LocalDate startDate, LocalDate endDate) {
            return taskRepo.findByCreatedAtBetween(startDate,endDate);
        }


    
    

}

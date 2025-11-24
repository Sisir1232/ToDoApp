package com.basnet.todoapp.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.basnet.todoapp.entities.Task;
import com.basnet.todoapp.exception.ResourceNotFoundException;
import com.basnet.todoapp.repo.TaskRepo;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;

	public Task  saveTask(Task task) {
        log.info("creating a task with title:{}",task.getTitle());
		return taskRepo.save(task);
	}

    public Page<Task> getTasks(Pageable pageable) {
        log.info("fetching all the saved tasks");
       return  taskRepo.findAll(pageable);
    }

    public Task getTaskById(Long id) {
        log.info("fetching a task with task id :{}",id);
       return  taskRepo.findById(id)
                      .orElseThrow(()->new ResourceNotFoundException("not found with id "+ id));
    }

    public Task updateTask(Long id, Task updatedTask) {
        log.info("updating the task by id {}",id);
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
        log.info("deleting a task with id:{}",id);
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

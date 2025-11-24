package com.basnet.todoapp.controller;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.basnet.todoapp.entities.Task;
import com.basnet.todoapp.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@Tag(name="tasks API",description="API for simple CRUD operation and filter the tasks")
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("/create")
    @Operation(summary = "Create a new task", description = "Creates a new task and returns the saved task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Get all tasks", description = "Returns a paginated list of all tasks")
    public ResponseEntity<Page<Task>> getTasks(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        PageRequest pageable=PageRequest.of(page,size);
        Page<Task> tasks = taskService.getTasks(pageable);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID", description = "Returns a task matching the given ID")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a task", description = "Updates the entire task object for the given ID")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Partially update a task", description = "Updates specific fields of a task for the given ID")
    public ResponseEntity<Task> patchTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a task", description = "Deletes the task with the given ID")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        Task task = taskService.deleteTask(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/uncompleted")
    @Operation(summary = "Get all uncompleted tasks", description = "Returns a list of tasks that are not yet completed")
    public ResponseEntity<List<Task>> getUncompletedTasks() {
        List<Task> tasks = taskService.getUncompletedTasks();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/completed")
    @Operation(summary = "Get all completed tasks", description = "Returns a list of tasks that are marked as completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        List<Task> tasks = taskService.getCompletedTasks();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/filter")
    @Operation(summary = "Get all uncompleted tasks", description = "Returns a list of tasks that are not yet completed")
    public ResponseEntity<List<Task>> getTaskByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Task> tasks = taskService.getTaskByDate(startDate, endDate);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}


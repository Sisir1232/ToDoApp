package com.basnet.todoapp.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basnet.todoapp.entities.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    public List<Task> findByCompletedFalse();
    public List<Task> findByCompletedTrue();
    public List<Task> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
    public Task getTaskByid();


}

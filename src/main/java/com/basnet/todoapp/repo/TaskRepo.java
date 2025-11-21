package com.basnet.todoapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basnet.todoapp.entities.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

}

package com.basnet.todoapp.ServiceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.basnet.todoapp.entities.Task;
import com.basnet.todoapp.repo.TaskRepo;
import com.basnet.todoapp.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    
    @Mock
    private TaskRepo mockRepo;
    @InjectMocks
    private TaskService mockService;
    @Test
    void testGetTaskByid(){

        Task task=new Task();
        task.setId(1L);
        when(mockRepo.findById(1L)).thenReturn(Optional.of(task));
        task.setTitle("learning unit testing");
        Task resultTask=mockService.getTaskById(1L);
        assertEquals("learning unit testing",resultTask.getTitle());
        verify(mockRepo).findById(1L);
    }
    
             
    
}

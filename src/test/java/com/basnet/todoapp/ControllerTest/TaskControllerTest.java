package com.basnet.todoapp.ControllerTest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.basnet.todoapp.controller.TaskController;
import com.basnet.todoapp.entities.Task;
import com.basnet.todoapp.service.TaskService;
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService mockService;

    @Test
    void testTaskApi() throws Exception {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setTitle("learning unit test");

        when(mockService.getTaskById(1L)).thenReturn(task);
        mockMvc.perform(get("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("learning unit test"));
        Mockito.verify(mockService).getTaskById(1L);
    }
}


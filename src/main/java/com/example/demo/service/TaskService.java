package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<TaskDto> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(TaskDto task);
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);

}


package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    public TaskServiceImpl() {
        modelMapper = new ModelMapper();
    }


    @Override
    public List<TaskDto> getAllTasks() {
        try {
            List<Task> tasks = taskRepository.findAll();
            return tasks.stream()
                    .map(data -> modelMapper.map(data, TaskDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotFoundException("Failed to retrieve banks: " + e.getMessage());
        }
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        try {
            Task task = modelMapper.map(taskDto, Task.class);
            Task savedTask = taskRepository.save(task);
            return modelMapper.map((Object) savedTask, (Type) TaskDto.class);
        } catch (Exception e) {
            throw new NotFoundException("Failed to create owner: " + e.getMessage());
        }
    }
    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setDue_date(taskDetails.getDue_date());
            task.setStatus(taskDetails.getStatus());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/tasks")
//@RequiredArgsConstructor
//public class TaskController {
//    private final TaskService taskService;
//
//    @GetMapping
//    public ResponseEntity<List<TaskDto>> getAllTask(){
//        List<TaskDto> tasks =taskService.getAllTask();
//        return  ResponseEntity.ok(tasks);
//    }
//
//}
@RestController
@RequestMapping(name="/api")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasks =taskService.getAllTasks();
        return  ResponseEntity.ok(tasks);
    }

    @GetMapping("/{Id}")
    public Task getTask(@PathVariable int taskId){
        Task theTask = taskService.getTaskById((long) taskId);

        if (theTask == null) {
            throw new NotFoundException("Task id not found - " + taskId);
        }
        return theTask;
    }

    @PostMapping("")
    public <PostException extends Throwable> ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        try {
            Task savedTaskDto =taskService.createTask(taskDto);
            return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

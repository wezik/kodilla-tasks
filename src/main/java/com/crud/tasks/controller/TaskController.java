package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final DbService dbService;
    private final TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = dbService.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "tasks/{taskId}")
    public TaskDto getTask(@PathVariable long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(
                dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new)
        );
    }

    @DeleteMapping(value = "tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
        Task task = dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new);
        dbService.deleteTask(task);
    }

    @PutMapping(value = "tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = dbService.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }

    @PostMapping(value = "tasks")
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);
    }
}

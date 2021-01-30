package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    void shouldReturnProperListOfTaskDto() {
        // Given
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1L,"Test","test_content");
        taskList.add(task1);
        Task task2 = new Task(2L,"Test","test_content");
        taskList.add(task2);

        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L,"Test","test_content");
        taskDtoList.add(taskDto1);
        TaskDto taskDto2 = new TaskDto(2L,"Test","test_content");
        taskDtoList.add(taskDto2);

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtoList);

        // When
        List<TaskDto> result = taskController.getTasks();

        // Then
        assertNotNull(result);
        result.forEach(e -> assertEquals(e,taskDtoList.get(e.getId().intValue()-1)));
    }

    @Test
    void shouldThrowTaskNotFoundExceptionForGetting() {
        // Given
        Optional<Task> task = Optional.empty();
        when(dbService.getTask(1L)).thenReturn(task);

        // When

        // Then
        assertThrows(TaskNotFoundException.class, ()-> taskController.getTask(1L));
    }

    @Test
    void shouldThrowTaskNotFoundExceptionForDeleting() {
        // Given
        Optional<Task> task = Optional.empty();
        when(dbService.getTask(1L)).thenReturn(task);

        // When

        // Then
        assertThrows(TaskNotFoundException.class, ()-> taskController.deleteTask(1L));
    }
}
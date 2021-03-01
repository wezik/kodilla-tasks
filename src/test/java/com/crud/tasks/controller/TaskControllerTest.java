package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldReturnTaskDtos() throws Exception {
        // Given
        List<Task> taskList = List.of(
                new Task(1L,"Test","test_content"),
                new Task(2L,"Test","test_content"));

        when(dbService.getAllTasks()).thenReturn(taskList);

        List<TaskDto> taskDtoList = List.of(
                new TaskDto(1L,"Test","test_content"),
                new TaskDto(2L,"Test","test_content"));

        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtoList);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test_content")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("test_content")));
    }

    @Test
    void shouldReturnTaskDto() throws Exception {
        // Given
        Optional<Task> task = Optional.of(new Task(1L, "Test", "test_content"));

        when(dbService.getTask(1)).thenReturn(task);

        TaskDto taskDto = new TaskDto(1L,"Test","test_content");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test_content")));
    }

    @Test
    void shouldReturnUpdatedTaskDto() throws Exception {
        // Given
        Task task = new Task(1L, "Test", "test_content");

        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        TaskDto taskDto = new TaskDto(1L,"Test","test_content");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);

        Gson gson = new Gson();
        String content = gson.toJson(task);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test_content")));
    }
}

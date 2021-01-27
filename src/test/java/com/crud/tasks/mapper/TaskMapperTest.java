package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(5L,"Title","content");

        //When
        Task result = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(result.getTitle(),taskDto.getTitle());
        assertEquals(result.getContent(),taskDto.getContent());
        assertEquals(result.getId(),taskDto.getId());

    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(6L,"TiTlE","CoNteNt");

        //When
        TaskDto result = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(result.getTitle(),task.getTitle());
        assertEquals(result.getContent(),task.getContent());
        assertEquals(result.getId(),task.getId());

    }

    @Test
    void testMapToTaskDtoList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(6L,"TiTlE","CoNteNt"));
        taskList.add(new Task(7L,"titur","description"));
        taskList.add(new Task(8L,"tested","boy"));

        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);

        //Then
        for (int i = 0; i < result.size(); i++){
            assertEquals(result.get(i).getTitle(), taskList.get(i).getTitle());
            assertEquals(result.get(i).getContent(), taskList.get(i).getContent());
            assertEquals(result.get(i).getId(), taskList.get(i).getId());
        }

    }
}
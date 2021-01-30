package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    void shouldReturnEmptyList() {
        // Given
        List<Task> testList = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(testList);

        // When
        List<Task> resultList = dbService.getAllTasks();

        // Then
        assertNotNull(resultList);
        assertEquals(resultList.size(),0);
    }

    @Test
    void shouldReturnOptional() {
        // Given
        Optional<Task> task = Optional.empty();
        Mockito.when(repository.findById(1)).thenReturn(task);

        // When
        Optional<Task> result = dbService.getTask(1);

        // Then
        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnSavedTask() {
        // Given
        Task task = new Task(2L,"test","test_content");
        Mockito.when(repository.save(Mockito.any())).thenReturn(task);

        // When
        Task result = dbService.saveTask(task);

        // Then
        assertNotNull(result);
        assertEquals(result,task);
    }

}

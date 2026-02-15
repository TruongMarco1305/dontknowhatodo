package io.github.dontknowhatodo.task;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.dontknowhatodo.exception.InternalServerErrorException;
import io.github.dontknowhatodo.task.dto.TaskCreateRequestDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    @Transactional()
    public Task createTask(UUID userId, TaskCreateRequestDto taskCreateRequestDto) {
        try {
            Task task = new Task(userId, taskCreateRequestDto);
            return repository.save(task);
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to create task: " + e.getMessage());
        }
    }
}

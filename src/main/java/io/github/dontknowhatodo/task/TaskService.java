package io.github.dontknowhatodo.task;

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
    public Task createTask(String userId, TaskCreateRequestDto taskCreateRequestDto) {
        try {
            Task task = new Task(userId, taskCreateRequestDto);
            return repository.save(task);
        } catch (Exception e) {
            throw new InternalServerErrorException("Something went wrong. Please try again later");
        }
    }
}

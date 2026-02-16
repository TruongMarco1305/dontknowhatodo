package io.github.dontknowhatodo.task;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.dontknowhatodo.enums.TaskStatus;
import io.github.dontknowhatodo.exception.InternalServerErrorException;
import io.github.dontknowhatodo.task.dto.TaskCreateRequestDto;
import io.github.dontknowhatodo.task.dto.TaskUpdateRequestDto;
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

    public Task deleteTask(UUID userId, String taskId) {
        Task task = repository.findByIdAndOwnerId(UUID.fromString(taskId), userId)
                .orElseThrow(() -> new InternalServerErrorException("Task not found or you don't have permission to delete it."));
        repository.delete(task);
        return task;
    }
 
    public Task changeArchiveStatus(UUID userId, String taskId) {
        Task task = repository.findByIdAndOwnerId(UUID.fromString(taskId), userId)
                .orElseThrow(() -> new InternalServerErrorException("Task not found or you don't have permission to archive it."));
        task.setIsArchived(!task.getIsArchived());
        return repository.save(task);
    }

    public Task updateTask(UUID userId, String taskId, TaskUpdateRequestDto taskUpdateRequestDto) {
        Task task = repository.findByIdAndOwnerId(UUID.fromString(taskId), userId)
                .orElseThrow(() -> new InternalServerErrorException("Task not found or you don't have permission to update it."));
        task.update(taskUpdateRequestDto);
        return repository.save(task);
    }

    public Task changeTaskStatus(UUID userId, String taskId, TaskStatus newStatus) {
        Task task = repository.findByIdAndOwnerId(UUID.fromString(taskId), userId)
                .orElseThrow(() -> new InternalServerErrorException("Task not found or you don't have permission to update it."));
        task.setStatus(newStatus);
        return repository.save(task);
    }

    public List<Task> getArchivedTasks(UUID userId, int page, int pageSize) {
        return repository.findByOwnerIdAndIsArchived(userId, true).stream().skip((page - 1) * pageSize).limit(pageSize + 1).toList();
    }
}

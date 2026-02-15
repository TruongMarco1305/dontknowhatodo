package io.github.dontknowhatodo.task.dto;

import io.github.dontknowhatodo.task.Task;
import lombok.Getter;

@Getter
public class TaskResponseDto {
    private final String id;
    private final String title;
    private final String description;
    private final String status;
    private final String priority;
    private final String deadline;
    private final String createdAt;
    private final String updatedAt;

    public TaskResponseDto(Task entity) {
        this.id =  entity.getId().toString();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.status = entity.getStatus().toString();
        this.priority = entity.getPriority().toString();
        this.deadline = entity.getDeadline() != null ? entity.getDeadline().toString() : null;
        this.createdAt = entity.getCreatedAt().toString();
        this.updatedAt = entity.getUpdatedAt().toString();
    }
}
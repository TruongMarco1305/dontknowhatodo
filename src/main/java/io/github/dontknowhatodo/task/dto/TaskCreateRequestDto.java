package io.github.dontknowhatodo.task.dto;

import io.github.dontknowhatodo.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TaskCreateRequestDto {
    @NotBlank(message = "Priority is required")
    private TaskPriority priority;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String deadline;
}

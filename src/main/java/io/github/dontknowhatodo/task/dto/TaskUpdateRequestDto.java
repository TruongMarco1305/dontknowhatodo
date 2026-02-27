package io.github.dontknowhatodo.task.dto;

import io.github.dontknowhatodo.enums.TaskPriority;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TaskUpdateRequestDto {
    private TaskPriority priority;
    private String title;
    private String description;
    private String deadline;
}

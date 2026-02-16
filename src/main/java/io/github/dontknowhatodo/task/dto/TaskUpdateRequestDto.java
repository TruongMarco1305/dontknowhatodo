package io.github.dontknowhatodo.task.dto;

import java.util.Optional;

import io.github.dontknowhatodo.enums.TaskPriority;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TaskUpdateRequestDto {
    private Optional<TaskPriority> priority;
    private Optional<String> title;
    private Optional<String> description;
    private Optional<String> deadline;
}

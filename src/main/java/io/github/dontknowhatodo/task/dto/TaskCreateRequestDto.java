package io.github.dontknowhatodo.task.dto;

import java.util.Optional;

import io.github.dontknowhatodo.annonation.ValueOfEnum;
import io.github.dontknowhatodo.enums.TaskPriority;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TaskCreateRequestDto {
    @ValueOfEnum(enumClass = TaskPriority.class, message = "priority must be LOW, MEDIUM, or HIGH")
    private String priority;
    private String title;
    private Optional<String> description;
    private Optional<String> deadline;
    // public Task toEntity() {
    //     return Task.builder()
    //             .title(this.title)
    //             .description(this.description.orElse(null))
    //             .status(TaskStatus.TODO)
    //             .priority(TaskPriority.valueOf(this.priority.toUpperCase()))
    //             .deadline(this.deadline.isPresent() ? LocalDateTime.parse(this.deadline.get()) : null)
    //             .build();
    // }
}

package io.github.dontknowhatodo.task.dto;

import java.util.List;

import io.github.dontknowhatodo.task.Task;
import lombok.Getter;

@Getter
public class TaskPaginationResponseDto {
    private final List<TaskResponseDto> data;
    private final Boolean isPrevious;
    private final Boolean isNext;

    public TaskPaginationResponseDto(List<Task> tasks, int page, int pageSize){
        this.data = tasks.stream().map(TaskResponseDto::new).toList().subList(0, Math.min(tasks.size(), pageSize));
        this.isPrevious = page != 1;
        this.isNext = tasks.size() > pageSize;
    }
}
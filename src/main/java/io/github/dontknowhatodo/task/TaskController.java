package io.github.dontknowhatodo.task;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import io.github.dontknowhatodo.annonation.User;
import io.github.dontknowhatodo.enums.TaskStatus;
import io.github.dontknowhatodo.task.dto.TaskCreateRequestDto;
import io.github.dontknowhatodo.task.dto.TaskPaginationResponseDto;
import io.github.dontknowhatodo.task.dto.TaskResponseDto;
import io.github.dontknowhatodo.task.dto.TaskUpdateRequestDto;
import io.github.dontknowhatodo.user.UserPrincipal;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @MutationMapping
    public TaskResponseDto createTask(@User UserPrincipal userPrincipal, @Argument("data") TaskCreateRequestDto taskCreateRequestDto) {
        Task task = taskService.createTask(userPrincipal.getId(), taskCreateRequestDto);
        return new TaskResponseDto(task);
    }

    @MutationMapping
    public TaskResponseDto deleteTask(@User UserPrincipal userPrincipal, @Argument String taskId) {
        Task task = taskService.deleteTask(userPrincipal.getId(), taskId);
        return new TaskResponseDto(task);
    }

    @MutationMapping
    public TaskResponseDto changeArchiveStatus(@User UserPrincipal userPrincipal, @Argument String taskId) {
        Task task = taskService.changeArchiveStatus(userPrincipal.getId(), taskId);
        return new TaskResponseDto(task);
    }

    @MutationMapping
    public TaskResponseDto updateTask(@User UserPrincipal userPrincipal, @Argument String taskId, @Argument("data") TaskUpdateRequestDto taskUpdateRequestDto) {
        Task task = taskService.updateTask(userPrincipal.getId(), taskId, taskUpdateRequestDto);
        return new TaskResponseDto(task);
    }

    @MutationMapping
    public TaskResponseDto changeTaskStatus(@User UserPrincipal userPrincipal, @Argument String taskId, @Argument TaskStatus newStatus) {
        Task task = taskService.changeTaskStatus(userPrincipal.getId(), taskId, newStatus);
        return new TaskResponseDto(task);
    }

    @QueryMapping
    public TaskPaginationResponseDto getArchivedTasks(@User UserPrincipal userPrincipal, @Argument int page, @Argument int pageSize) {
        List<Task> tasks = taskService.getArchivedTasks(userPrincipal.getId(), page, pageSize);
        return new TaskPaginationResponseDto(tasks, page, pageSize);
    }
} 
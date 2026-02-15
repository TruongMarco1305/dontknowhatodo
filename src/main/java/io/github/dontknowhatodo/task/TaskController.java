package io.github.dontknowhatodo.task;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import io.github.dontknowhatodo.annonation.User;
import io.github.dontknowhatodo.task.dto.TaskCreateRequestDto;
import io.github.dontknowhatodo.task.dto.TaskResponseDto;
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
} 
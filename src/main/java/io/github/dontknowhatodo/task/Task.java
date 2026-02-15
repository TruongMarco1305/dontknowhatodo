package io.github.dontknowhatodo.task;

import java.time.LocalDateTime;
import java.util.UUID;

import io.github.dontknowhatodo.config.BaseEntityConfig;
import io.github.dontknowhatodo.enums.TaskPriority;
import io.github.dontknowhatodo.enums.TaskStatus;
import io.github.dontknowhatodo.task.dto.TaskCreateRequestDto;
import io.github.dontknowhatodo.user.UserInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntityConfig {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @Column()
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "_id", nullable = false)
    private UserInfo owner;

    public Task(UUID userId, TaskCreateRequestDto taskCreateRequestDto) {
        this.title = taskCreateRequestDto.getTitle();
        this.description = taskCreateRequestDto.getDescription().orElse(null);
        this.status = TaskStatus.TODO;
        this.priority = TaskPriority.valueOf(taskCreateRequestDto.getPriority().toUpperCase());
        this.deadline = taskCreateRequestDto.getDeadline().isPresent() ? LocalDateTime.parse(taskCreateRequestDto.getDeadline().get()) : null;
        this.owner = new UserInfo();
        this.owner.setId(userId);
    }
}
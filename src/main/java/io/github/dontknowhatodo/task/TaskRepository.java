package io.github.dontknowhatodo.task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findByTitle(String title);
    Optional<Task> findByIdAndOwnerId(UUID taskId, UUID ownerId);
    List<Task> findByOwnerId(UUID ownerId);
    List<Task> findByOwnerIdAndIsArchived(UUID ownerId, Boolean isArchived);
}
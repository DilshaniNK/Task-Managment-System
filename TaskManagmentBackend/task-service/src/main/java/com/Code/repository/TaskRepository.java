package com.Code.repository;

import com.Code.modal.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findByAssignUserId(Long userId);

}

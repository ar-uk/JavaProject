package com.adil.unisiteproject.javaproject.repositories;

import com.adil.unisiteproject.javaproject.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByGroupId(Long groupId, Pageable pageable);
    List<Task> findByIdIn(List<Long> ids);
    List<Task> findByTeacherId(Long teacherId);
}

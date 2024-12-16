package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Task;
import com.adil.unisiteproject.javaproject.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        // Save a new task
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        // Get task by ID
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByGroupId(Long groupId) {
        // Fetch tasks for a specific group
        return taskRepository.findByGroupId(groupId);
    }

    public void deleteTask(Long id) {
        // Delete task by ID
        taskRepository.deleteById(id);
    }
}
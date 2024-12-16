package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Task;
import com.adil.unisiteproject.javaproject.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private void validateTask(Task task) {
        // Validate critical fields
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or blank.");
        }
        if (task.getTeacher() == null || task.getTeacher().getName() == null || task.getTeacher().getName().isBlank()) {
            throw new IllegalArgumentException("Task teacher cannot be null or blank.");
        }
    }

    public Task createTask(Task task, Long groupId) {
        // Set the group in the task
        task.setGroup(groupId);
        // Validate critical fields
        validateTask(task);
        // Save and return the task
        return taskRepository.save(task);
    }

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StudentService studentService;

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

    public List<Task> getNewTasksForStudent() {
        Long groupId = studentService.getGroupIdByStudentId(studentId);
        return taskRepository.findByGroupIdAndCreationDateAfter(groupId, sinceDate);
    }
}

package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Group;
import com.adil.unisiteproject.javaproject.models.Task;
import com.adil.unisiteproject.javaproject.repositories.TaskRepository;
import com.adil.unisiteproject.javaproject.models.Submission;
import com.adil.unisiteproject.javaproject.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private GroupService groupService;

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
        // Fetch the group and set it in the task
        Group group = groupService.getGroupById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));

        task.setGroup(group);
        // Validate the Task
        validateTask(task);

        // Save the Task
        return taskRepository.save(task);
    }

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubmissionService submissionService;

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

    public List<Task> getNewTasksForStudent(Long studentId) {
        // Fetch the group ID for the student
        Long groupId = studentService.getGroupIdByStudentId(studentId);

        // Fetch all tasks for the student's group
        List<Task> groupTasks = taskRepository.findByGroupId(groupId);

        // Filter tasks: Exclude those that the student has already submitted
        return groupTasks.stream()
                .filter(task -> !submissionService.isTaskSubmittedByStudent(task.getId(), studentId))
                .toList();
    }

    public List<Task> getSubmittedTasksByStudent(Long studentId) {
        // Get all submission IDs (Task IDs) for the given student
        List<Long> submittedTaskIds = submissionService.getSubmittedTaskIdsByStudent(studentId);

        // Fetch and return all tasks associated with these task IDs
        return taskRepository.findByIdIn(submittedTaskIds);
    }

    public List<Task> getTasksByTeacherId(Long teacherId) {
        // Fetch and return tasks assigned to the given teacher based on their ID
        return taskRepository.findByTeacherId(teacherId);
    }

    public List<Submission> getTaskSubmissions(Long taskId) {
        // Fetch and return submissions for the given task ID
        return submissionService.getSubmissionsByTaskId(taskId);
    }
}

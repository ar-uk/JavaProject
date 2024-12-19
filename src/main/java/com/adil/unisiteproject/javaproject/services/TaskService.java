package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Group;
import com.adil.unisiteproject.javaproject.models.Task;
import com.adil.unisiteproject.javaproject.repositories.TaskRepository;
import com.adil.unisiteproject.javaproject.models.Submission;
import com.adil.unisiteproject.javaproject.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import lombok.*;

@Data
@Service
public class TaskService {
    @Autowired
    private GroupService groupService;

    private void validateTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or blank.");
        }
        if (task.getTeacher() == null || task.getTeacher().getName() == null || task.getTeacher().getName().isBlank()) {
            throw new IllegalArgumentException("Task teacher cannot be null or blank.");
        }
    }

    public Task createTask(Task task, Long groupId) {
        Group group = groupService.getGroupById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID"));

        task.setGroup(group);
        validateTask(task);

        return taskRepository.save(task);
    }

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private StudentService studentService;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Page<Task> findByGroupId(Long groupId, Pageable pageable) {
        return taskRepository.findByGroupId(groupId, pageable);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getNewTasksForStudent(Long studentId) {
        Long groupId = studentService.getGroupIdByStudentId(studentId);
        List<Task> groupTasks = taskRepository.findByGroupId(groupId, Pageable.unpaged()).getContent();
        return groupTasks.stream()
                .filter(task -> !submissionService.isTaskSubmittedByStudent(task.getId(), studentId))
                .toList();
    }


    public List<Task> getSubmittedTasksByStudent(Long studentId) {
        List<Long> submittedTaskIds = submissionService.getSubmittedTaskIdsByStudent(studentId);

        return taskRepository.findByIdIn(submittedTaskIds);
    }

    public List<Task> getTasksByTeacherId(Long teacherId) {
        return taskRepository.findByTeacherId(teacherId);
    }

    public List<Submission> getTaskSubmissions(Long taskId) {
        return submissionService.getSubmissionsByTaskId(taskId);
    }
}

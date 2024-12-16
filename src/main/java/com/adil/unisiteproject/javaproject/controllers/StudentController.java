package com.adil.unisiteproject.javaproject.controllers;

import com.adil.unisiteproject.javaproject.models.Submission;
import com.adil.unisiteproject.javaproject.services.StudentService;
import com.adil.unisiteproject.javaproject.services.TaskService;
import com.adil.unisiteproject.javaproject.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private TaskService taskService; // Ensure TaskService implementation is annotated with @Service

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/dashboard")
    public String studentDashboard(Model model) {
        // Show new tasks and submitted tasks
        // Add logic to display lists
        return "student_dashboard";
    }

    @GetMapping("/tasks/new")
    public String newTasks(Model model) {
        model.addAttribute("tasks", taskService.getNewTasksForStudent());
        return "new_tasks";
    }

    @GetMapping("/tasks/{taskId}/submit")
    public String submitTaskPage(@PathVariable Long taskId, Model model) {
        model.addAttribute("submission", new Submission());
        model.addAttribute("taskId", taskId);
        return "submit_task";
    }

    @PostMapping("/tasks/{taskId}/submit")
    public String submitTask(@PathVariable Long taskId, Submission submission) {
        submissionService.createSubmission(submission, taskId);
        return "redirect:/student/dashboard";
    }
}
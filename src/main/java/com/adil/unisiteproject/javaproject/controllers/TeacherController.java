package com.adil.unisiteproject.controllers;

import com.adil.unisiteproject.models.Task;
import com.adil.unisiteproject.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/dashboard")
    public String teacherDashboard(Model model) {
        // Fetch all groups/related tasks for the teacher.
        // Add logic to display lists...
        return "teacher_dashboard"; // Render teacher dashboard Thymeleaf template
    }

    @GetMapping("/groups/{groupId}/tasks")
    public String groupTasks(@PathVariable Long groupId, Model model) {
        model.addAttribute("tasks", taskService.getTasksByGroupId(groupId));
        return "group_tasks";
    }

    @GetMapping("/groups/{groupId}/tasks/create")
    public String createTaskPage(@PathVariable Long groupId, Model model) {
        model.addAttribute("task", new Task()); // Form Binding
        return "create_task";
    }

    @PostMapping("/groups/{groupId}/tasks/create")
    public String createTask(@PathVariable Long groupId, Task task) {
        taskService.createTask(task, groupId);
        return "redirect:/teacher/groups/" + groupId + "/tasks";
    }

    @PostMapping("/tasks/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/teacher/dashboard";
    }
}
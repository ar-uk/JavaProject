package com.adil.unisiteproject.javaproject.controllers;

import com.adil.unisiteproject.javaproject.models.Task;
import com.adil.unisiteproject.javaproject.services.TeacherService;
import com.adil.unisiteproject.javaproject.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
@GetMapping("/tasks/manage")
public String manageTasks(Model model) {
    String teacherEmail = teacherService.getAuthenticatedTeacherEmail();
    Long teacherId = teacherService.getTeacherByEmail(teacherEmail).getId();
    model.addAttribute("tasks", taskService.getTasksByTeacherId(teacherId));
    return "manage_tasks";
}
@GetMapping("/tasks/{taskId}/submissions")
public String reviewTaskSubmissions(@PathVariable Long taskId, Model model) {
    model.addAttribute("submissions", taskService.getTaskSubmissions(taskId));
    return "review";
}

    @Autowired
    private TaskService taskService;

    @Autowired
    private TeacherService teacherService;

@GetMapping("/dashboard")
public String teacherDashboard(Model model) {
    String teacherEmail = teacherService.getAuthenticatedTeacherEmail();
    Long teacherId = teacherService.getTeacherByEmail(teacherEmail).getId();
    model.addAttribute("groups", teacherService.getGroupsByTeacherId(teacherId));
    model.addAttribute("tasks", taskService.getTasksByTeacherId(teacherId));
    return "teacher_dashboard";
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
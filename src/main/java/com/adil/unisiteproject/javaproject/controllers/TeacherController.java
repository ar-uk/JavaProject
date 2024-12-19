package com.adil.unisiteproject.javaproject.controllers;

import com.adil.unisiteproject.javaproject.models.Task;
import com.adil.unisiteproject.javaproject.models.Teacher;
import com.adil.unisiteproject.javaproject.services.TeacherService;
import com.adil.unisiteproject.javaproject.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String groupTasks(@PathVariable Long groupId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, Model model) {
        Page<Task> taskPage = taskService.findByGroupId(groupId, PageRequest.of(page, size));
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("currentPage", taskPage.getNumber());
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("groupId", groupId);
        return "group_tasks";
    }

    @GetMapping("/groups/{groupId}/tasks/create")
    public String createTaskPage(@PathVariable Long groupId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("groupId", groupId);
        return "create_task";
    }

    @PostMapping("/groups/{groupId}/tasks/create")
    public String createTask(@PathVariable Long groupId, Task task) {
        String teacherEmail = teacherService.getAuthenticatedTeacherEmail();
        Teacher teacher = teacherService.getTeacherByEmail(teacherEmail);

        task.setTeacher(teacher);
        taskService.createTask(task, groupId);
        return "redirect:/teacher/groups/" + groupId + "/tasks";
    }

    @PostMapping("/tasks/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/teacher/dashboard";
    }
}
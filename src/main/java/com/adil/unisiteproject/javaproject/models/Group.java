package com.adil.unisiteproject.javaproject.models;

import jakarta.persistence.*;
import java.util.List;
import com.adil.unisiteproject.javaproject.models.Teacher;

@Entity
@Table(name = "groups") // Avoid conflicts with SQL reserved keywords like "group"
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupNumber; // A unique identifier for the group (e.g., "Group 1")

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students; // List of students in the group

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks; // Tasks assigned to this group

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    // Default Constructor
    public Group() {
    }

    // Parameterized Constructor
    public Group(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    // toString method for debugging/logging
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupNumber='" + groupNumber + '\'' +
                ", teacher=" + teacher +
                '}';
    }

    // equals and hashCode methods (to handle comparisons and collections)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id != null && id.equals(group.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
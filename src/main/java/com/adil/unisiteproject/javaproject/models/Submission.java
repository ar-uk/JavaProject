package com.adil.unisiteproject.javaproject.models;

import jakarta.persistence.*;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id") // Foreign key to Task
    private Task task;

    @ManyToOne
    @JoinColumn(name = "student_id") // Foreign key to Student
    private Student student;

    @Column(nullable = false)
    private String submissionType; // "file" or "github"

    @Column(nullable = false)
    private String submissionData; // File path or GitHub URL

    // Default Constructor
    public Submission() {
    }

    // Parameterized Constructor
    public Submission(Task task, Student student, String submissionType, String submissionData) {
        this.task = task;
        this.student = student;
        this.submissionType = submissionType;
        this.submissionData = submissionData;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    public String getSubmissionData() {
        return submissionData;
    }

    public void setSubmissionData(String submissionData) {
        this.submissionData = submissionData;
    }

    // toString Method for Debugging
    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", task=" + ((task != null) ? task.getId() : null) + // Avoid cyclic calls
                ", student=" + ((student != null) ? student.getId() : null) +
                ", submissionType='" + submissionType + '\'' +
                ", submissionData='" + submissionData + '\'' +
                '}';
    }

    // equals and hashCode Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
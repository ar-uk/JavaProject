package com.adil.unisiteproject.javaproject.models;

import jakarta.persistence.*;

import java.util.Optional;
import lombok.*;

@Data
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private String submissionType;

    @Column(nullable = false)
    private String submissionData;


    public Submission() {
    }


    public Submission(Task task, Student student, String submissionType, String submissionData) {
        this.task = task;
        this.student = student;
        this.submissionType = submissionType;
        this.submissionData = submissionData;
    }

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
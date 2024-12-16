package com.adil.unisiteproject.javaproject.models;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private String attachmentUrl; // Optional field for file URLs or attachments

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false) // Foreign key to Teacher
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false) // Foreign key to Group
    private Group group;

    // Default Constructor
    public Task() {
    }

    // Parameterized Constructor
    public Task(String title, String description, String attachmentUrl, Teacher teacher, Group group) {
        this.title = title;
        this.description = description;
        this.attachmentUrl = attachmentUrl;
        this.teacher = teacher;
        this.group = group;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    // toString Method for Debugging
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                ", teacher=" + ((teacher != null) ? teacher.getId() : null) + // Avoid cyclic references
                ", group=" + ((group != null) ? group.getId() : null) +
                '}';
    }

    // equals and hashCode Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id != null && id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public void setGroup(Long groupId) {
    }
}
package com.adil.unisiteproject.javaproject.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String role;


    public Student() {
    }

    public Student(String email, String name, String surname, Group group, String password, String role) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", group=" + ((group != null) ? group.getId() : null) + // Avoid cyclic toString calls
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id != null && id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
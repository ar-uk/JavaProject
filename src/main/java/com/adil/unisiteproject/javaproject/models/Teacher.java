package com.adil.unisiteproject.javaproject.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String profession;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public Teacher() {
    }

    public Teacher(String email, String name, String surname, String profession, String password, String role) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id != null && id.equals(teacher.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
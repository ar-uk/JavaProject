package com.adil.unisiteproject.javaproject.models;

import jakarta.persistence.*;
import lombok.Data; // Ensure Lombok is properly set up


@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String surname;
    private String profession;

    private String password; // Secured
}
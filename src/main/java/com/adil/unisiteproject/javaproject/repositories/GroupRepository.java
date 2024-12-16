package com.adil.unisiteproject.javaproject.repositories;

import com.adil.unisiteproject.javaproject.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    // You can add custom queries if needed later
}
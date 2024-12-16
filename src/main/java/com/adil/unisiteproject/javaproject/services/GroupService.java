package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Group;
import com.adil.unisiteproject.javaproject.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group saveGroup(Group group) {
        // Save a new group to the database
        return groupRepository.save(group);
    }

    public Optional<Group> getGroupById(Long id) {
        // Get group by ID
        return groupRepository.findById(id);
    }

    public List<Group> getAllGroups() {
        // Get a list of all groups
        return groupRepository.findAll();
    }

    public void deleteGroup(Long id) {
        // Delete a group
        groupRepository.deleteById(id);
    }
}
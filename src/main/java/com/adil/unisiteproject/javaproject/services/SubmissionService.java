package com.adil.unisiteproject.javaproject.services;

import com.adil.unisiteproject.javaproject.models.Submission;
import com.adil.unisiteproject.javaproject.models.Task;
import com.adil.unisiteproject.javaproject.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission saveSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    public Submission createSubmission(Submission submission, Long taskId) {
        Task task = new Task();
        task.setId(taskId);
        submission.setTask(task);

        if (submission.getSubmissionType() == null || submission.getSubmissionType().isEmpty()) {
            throw new IllegalArgumentException("Submission type cannot be null or empty");
        }
        if (submission.getSubmissionData() == null || submission.getSubmissionData().isEmpty()) {
            throw new IllegalArgumentException("Submission data cannot be null or empty");
        }

        return submissionRepository.save(submission);
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public List<Submission> getSubmissionsByTaskId(Long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    public List<Submission> getSubmissionsByStudentId(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }

    public boolean isTaskSubmittedByStudent(Long taskId, Long studentId) {
        return submissionRepository.existsByTaskIdAndStudentId(taskId, studentId);
    }


    public List<Long> getSubmittedTaskIdsByStudent(Long studentId) {
        List<Submission> submissions = submissionRepository.findByStudentId(studentId);
        return submissions.stream()
                .map(submission -> submission.getTask().getId())
                .distinct()
                .toList();
    }
}
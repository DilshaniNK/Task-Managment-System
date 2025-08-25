package com.Code.service;

import com.Code.modal.Submission;

import java.util.List;

public interface SubmissionService {
    Submission submitTask(Long taskId,String githubLink, Long userId, String jwt) throws Exception;

    Submission getTaskSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllTaskSubmissions() throws Exception;

    List<Submission> getAllSubmissionsByTaskId(Long taskId) throws Exception;

    Submission acceptDeclieneSubmission(Long id,String status) throws Exception;



}

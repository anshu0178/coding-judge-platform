package com.anshu.codingjudge.judgeservice.dto;

import com.anshu.codingjudge.judgeservice.entity.JudgeStatus;

public class EvaluateResponse {

    private Long submissionId;

    private JudgeStatus status;

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public JudgeStatus getStatus() {
        return status;
    }

    public void setStatus(JudgeStatus status) {
        this.status = status;
    }
}
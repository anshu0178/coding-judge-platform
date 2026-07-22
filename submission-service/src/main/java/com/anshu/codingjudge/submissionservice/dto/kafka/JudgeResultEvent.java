package com.anshu.codingjudge.submissionservice.dto.kafka;

public class JudgeResultEvent {

    private Long submissionId;
    private String status;

    public JudgeResultEvent() {
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
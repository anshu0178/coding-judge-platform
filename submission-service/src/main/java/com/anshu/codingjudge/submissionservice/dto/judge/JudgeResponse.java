package com.anshu.codingjudge.submissionservice.dto.judge;

public class JudgeResponse {

    private Long submissionId;

    private String status;

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
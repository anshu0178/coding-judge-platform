package com.anshu.codingjudge.judgeservice.dto;

public class EvaluateRequest {

    private Long submissionId;

    private String sourceCode;

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
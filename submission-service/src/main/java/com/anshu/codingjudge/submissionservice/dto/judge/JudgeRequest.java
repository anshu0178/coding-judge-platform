package com.anshu.codingjudge.submissionservice.dto.judge;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JudgeRequest {

    private Long submissionId;
    private String sourceCode;

//    public Long getSubmissionId() {
//        return submissionId;
//    }
//
//    public void setSubmissionId(Long submissionId) {
//        this.submissionId = submissionId;
//    }
}
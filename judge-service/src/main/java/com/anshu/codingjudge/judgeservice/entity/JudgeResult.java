package com.anshu.codingjudge.judgeservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "judge_results")
public class JudgeResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long submissionId;

    @Enumerated(EnumType.STRING)
    private JudgeStatus status;

    private String output;

    private LocalDateTime judgedAt;

    public JudgeResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public LocalDateTime getJudgedAt() {
        return judgedAt;
    }

    public void setJudgedAt(LocalDateTime judgedAt) {
        this.judgedAt = judgedAt;
    }
}
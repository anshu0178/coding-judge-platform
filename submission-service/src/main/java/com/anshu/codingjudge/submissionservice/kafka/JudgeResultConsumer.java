package com.anshu.codingjudge.submissionservice.kafka;

import com.anshu.codingjudge.submissionservice.dto.kafka.JudgeResultEvent;
import com.anshu.codingjudge.submissionservice.entity.Submission;
import com.anshu.codingjudge.submissionservice.entity.SubmissionStatus;
import com.anshu.codingjudge.submissionservice.repository.SubmissionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JudgeResultConsumer {

    private final SubmissionRepository submissionRepository;

    public JudgeResultConsumer(
            SubmissionRepository submissionRepository) {

        this.submissionRepository = submissionRepository;
    }

    @KafkaListener(
            topics = "judge-result-topic",
            groupId = "submission-group"
    )
    public void consumeJudgeResult(
            JudgeResultEvent event) {

        System.out.println(
                "Received Judge Result : "
                        + event.getSubmissionId()
        );

        Submission submission =
                submissionRepository
                        .findById(event.getSubmissionId())
                        .orElse(null);

        if (submission != null) {

            submission.setStatus(
                    SubmissionStatus.valueOf(
                            event.getStatus()
                    )
            );

            submissionRepository.save(submission);

            System.out.println(
                    "Submission Updated : "
                            + submission.getId()
            );
        }
    }
}
package com.anshu.codingjudge.judgeservice.kafka;

import com.anshu.codingjudge.judgeservice.dto.JudgeResultEvent;
import com.anshu.codingjudge.judgeservice.dto.SubmissionEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SubmissionConsumer {

    private final JudgeResultProducer producer;

    public SubmissionConsumer(
            JudgeResultProducer producer) {

        this.producer = producer;
    }

    @KafkaListener(
            topics = "submission-topic",
            groupId = "judge-group"
    )
    public void consumeSubmission(
            SubmissionEvent event) {

        System.out.println(
                "Received Submission : "
                        + event.getSubmissionId()
        );

        String status;

        if(event.getSourceCode().contains("class Solution")) {

            status = "ACCEPTED";

        } else {

            status = "WRONG_ANSWER";
        }

        JudgeResultEvent result =
                new JudgeResultEvent();

        result.setSubmissionId(
                event.getSubmissionId());

        result.setStatus(status);

        producer.sendResult(result);
    }
}
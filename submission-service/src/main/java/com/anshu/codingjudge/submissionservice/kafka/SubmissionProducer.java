package com.anshu.codingjudge.submissionservice.kafka;

import com.anshu.codingjudge.submissionservice.dto.kafka.SubmissionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubmissionProducer {

    private final KafkaTemplate<String, SubmissionEvent> kafkaTemplate;

    public SubmissionProducer(
            KafkaTemplate<String, SubmissionEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSubmission(
            SubmissionEvent event) {

        kafkaTemplate.send(
                "submission-topic",
                event
        );

        System.out.println(
                "Submission sent to Kafka: "
                        + event.getSubmissionId()
        );
    }
}
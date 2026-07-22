package com.anshu.codingjudge.judgeservice.kafka;

import com.anshu.codingjudge.judgeservice.dto.JudgeResultEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JudgeResultProducer {

    private final KafkaTemplate<String, JudgeResultEvent> kafkaTemplate;

    public JudgeResultProducer(
            KafkaTemplate<String, JudgeResultEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendResult(
            JudgeResultEvent event) {

        kafkaTemplate.send(
                "judge-result-topic",
                event
        );

        System.out.println(
                "Judge Result Sent : "
                        + event.getSubmissionId()
        );
    }
}
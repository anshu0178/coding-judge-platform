package com.anshu.codingjudge.judgeservice.kafka;

import com.anshu.codingjudge.judgeservice.client.ExecutionClient;
import com.anshu.codingjudge.judgeservice.client.ProblemClient;
import com.anshu.codingjudge.judgeservice.dto.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubmissionConsumer {

    private final JudgeResultProducer producer;
    private final ExecutionClient executionClient;
    private final ProblemClient problemClient;

    public SubmissionConsumer(
            JudgeResultProducer producer,
            ProblemClient problemClient,
            ExecutionClient executionClient) {

        this.producer = producer;
        this.problemClient = problemClient;
        this.executionClient = executionClient;
    }

    @KafkaListener(
            topics = "submission-topic",
            groupId = "judge-group"
    )
    public void consumeSubmission(SubmissionEvent event) {

        CreateContainerResponse createResponse =
                executionClient.createContainer();

        String containerName =
                createResponse.getContainerName();

        System.out.println(
                "Container Created : " + containerName
        );

        try {

            // =========================
            // Copy Java File
            // =========================

            CopyRequest copyRequest = new CopyRequest();

            copyRequest.setContainerName(containerName);
            copyRequest.setSourceCode(event.getSourceCode());

            CopyResponse copyResponse =
                    executionClient.copyJavaFile(copyRequest);

            System.out.println(
                    "Copy Success : "
                            + copyResponse.isSuccess()
            );

            if (!copyResponse.isSuccess()) {

                JudgeResultEvent result =
                        new JudgeResultEvent();

                result.setSubmissionId(
                        event.getSubmissionId()
                );

                result.setStatus("COPY_FAILED");

                producer.sendResult(result);

                return;
            }

            // =========================
            // Compile Java
            // =========================

            CompileRequest compileRequest =
                    new CompileRequest();

            compileRequest.setContainerName(
                    containerName
            );

            CompileResponse compileResponse =
                    executionClient.compileJava(
                            compileRequest
                    );

            System.out.println(
                    "Compile Success : "
                            + compileResponse.isSuccess()
            );

            if (!compileResponse.isSuccess()) {

                JudgeResultEvent result =
                        new JudgeResultEvent();

                result.setSubmissionId(
                        event.getSubmissionId()
                );

                result.setStatus("COMPILATION_ERROR");

                producer.sendResult(result);

                return;
            }

            // =========================
            // Fetch Test Cases
            // =========================

            System.out.println(
                    "Received Submission : "
                            + event.getSubmissionId()
            );

            System.out.println(
                    "Problem Id : "
                            + event.getProblemId()
            );

            List<TestCaseResponse> testCases =
                    problemClient.getTestCases(
                            event.getProblemId()
                    );

            System.out.println(
                    "Total Test Cases : "
                            + testCases.size()
            );

            boolean passed = true;

            // =========================
            // Run Test Cases
            // =========================

            for (TestCaseResponse testCase : testCases) {

                RunRequest runRequest =
                        new RunRequest();

                runRequest.setContainerName(
                        containerName
                );

                runRequest.setInput(
                        testCase.getInput()
                );

                RunResponse runResponse =
                        executionClient.runJava(
                                runRequest
                        );

                String output =
                        runResponse.getOutput();

                System.out.println(
                        "Input : "
                                + testCase.getInput()
                );

                System.out.println(
                        "Expected : "
                                + testCase.getExpectedOutput()
                );

                System.out.println(
                        "Actual : "
                                + output
                );

                if (!output.trim().equals(
                        testCase.getExpectedOutput().trim())) {

                    passed = false;
                    break;
                }
            }

            // =========================
            // Publish Result
            // =========================

            JudgeResultEvent result =
                    new JudgeResultEvent();

            result.setSubmissionId(
                    event.getSubmissionId()
            );

            if (passed) {

                result.setStatus("ACCEPTED");

            } else {

                result.setStatus("WRONG_ANSWER");

            }

            producer.sendResult(result);

        } finally {

            DeleteRequest deleteRequest =
                    new DeleteRequest();

            deleteRequest.setContainerName(
                    containerName
            );

            DeleteResponse deleteResponse =
                    executionClient.deleteContainer(deleteRequest);

            System.out.println(
                    "Container Deleted : "
                            + deleteResponse.isSuccess()
            );
        }
    }
}
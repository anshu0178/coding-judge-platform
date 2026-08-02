package com.anshu.codingjudge.executionservice.controller;

import com.anshu.codingjudge.executionservice.dto.*;
import com.anshu.codingjudge.executionservice.service.CodeExecutionService;
import com.anshu.codingjudge.executionservice.service.DockerService;
import com.anshu.codingjudge.executionservice.service.JavaFileService;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/execute")
public class ExecutionController {

    private final CodeExecutionService codeExecutionService;

    private final DockerService dockerService;

    private final JavaFileService javaFileService;

    public ExecutionController(
            CodeExecutionService codeExecutionService,
            DockerService dockerService,
            JavaFileService javaFileService) {

        this.codeExecutionService = codeExecutionService;
        this.dockerService = dockerService;
        this.javaFileService = javaFileService;
    }

    @PostMapping
    public ExecutionResponse execute(
            @RequestBody ExecutionRequest request) {

        String output = codeExecutionService.execute(
                request.getSourceCode(),
                request.getInput()
        );

        ExecutionResponse response = new ExecutionResponse();
        response.setOutput(output);

        return response;
    }


    @PostMapping("/container/create")
    public CreateContainerResponse createContainer() {

        String containerName =
                dockerService.createContainer();

        CreateContainerResponse response =
                new CreateContainerResponse();

        response.setContainerName(containerName);

        return response;
    }

    @PostMapping("/container/copy")
    public CopyFileResponse copyJavaFile(
            @RequestBody CopyFileRequest request) throws Exception {

        File javaFile =
                javaFileService.createJavaFile(
                        request.getSourceCode()
                );

        boolean copied =
                dockerService.copyJavaFile(
                        request.getContainerName(),
                        javaFile.getAbsolutePath()
                );

        CopyFileResponse response =
                new CopyFileResponse();

        response.setSuccess(copied);

        return response;
    }

    @PostMapping("/container/compile")
    public CompileResponse compile(
            @RequestBody CompileRequest request) {

        boolean compiled =
                dockerService.compileJava(
                        request.getContainerName()
                );

        CompileResponse response =
                new CompileResponse();

        response.setSuccess(compiled);

        return response;
    }

    @PostMapping("/container/run")
    public RunResponse run(
            @RequestBody RunRequest request) {

        String output =
                dockerService.runJava(
                        request.getContainerName(),
                        request.getInput()
                );

        RunResponse response =
                new RunResponse();

        response.setOutput(output);

        return response;
    }

    @PostMapping("/container/delete")
    public DeleteResponse delete(
            @RequestBody DeleteRequest request) {

        dockerService.deleteContainer(
                request.getContainerName()
        );

        DeleteResponse response =
                new DeleteResponse();

        response.setSuccess(true);

        return response;
    }

}
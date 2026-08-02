package com.anshu.codingjudge.judgeservice.client;

import com.anshu.codingjudge.judgeservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "EXECUTION-SERVICE")
public interface ExecutionClient {

    @PostMapping("/api/execute")
    ExecutionResponse execute(
            @RequestBody ExecutionRequest request
    );

    @PostMapping("/api/execute/container/create")
    CreateContainerResponse createContainer();

    @PostMapping("/api/execute/container/copy")
    CopyResponse copyJavaFile(
            @RequestBody CopyRequest request
    );

    @PostMapping("/api/execute/container/compile")
    CompileResponse compileJava(
            @RequestBody CompileRequest request
    );

    @PostMapping("/api/execute/container/run")
    RunResponse runJava(
            @RequestBody RunRequest request
    );

    @PostMapping("/api/execute/container/delete")
    DeleteResponse deleteContainer(
            @RequestBody DeleteRequest request
    );


}
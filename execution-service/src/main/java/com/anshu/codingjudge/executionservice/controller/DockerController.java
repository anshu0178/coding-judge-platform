package com.anshu.codingjudge.executionservice.controller;

import com.anshu.codingjudge.executionservice.service.DockerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

    private final DockerService dockerService;

    public DockerController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("/api/docker/create")
    public String createContainer() {

        String containerName = dockerService.createContainer();

        return "Container Created : " + containerName;
    }
}
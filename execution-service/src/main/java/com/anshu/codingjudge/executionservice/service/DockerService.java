package com.anshu.codingjudge.executionservice.service;

public interface DockerService {

    String createContainer();

    boolean copyJavaFile(
            String containerName,
            String javaFilePath
    );

    boolean compileJava(
            String containerName
    );

    String runJava(
            String containerName,
            String input
    );

    boolean deleteContainer(
            String containerName
    );

}
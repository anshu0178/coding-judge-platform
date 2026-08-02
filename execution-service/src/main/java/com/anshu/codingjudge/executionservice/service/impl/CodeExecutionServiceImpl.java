package com.anshu.codingjudge.executionservice.service.impl;

import com.anshu.codingjudge.executionservice.service.CodeExecutionService;
import com.anshu.codingjudge.executionservice.service.DockerService;
import com.anshu.codingjudge.executionservice.service.JavaFileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;

@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    private final DockerService dockerService;

    private final JavaFileService javaFileService;

    public CodeExecutionServiceImpl(
            DockerService dockerService,
            JavaFileService javaFileService) {

        this.dockerService = dockerService;
        this.javaFileService = javaFileService;
    }

    @Override
    public String execute(String sourceCode, String input) {

        String containerName = null;

        try {

            File javaFile =
                    javaFileService.createJavaFile(sourceCode);

            containerName =
                    dockerService.createContainer();

            boolean copied =
                    dockerService.copyJavaFile(
                            containerName,
                            javaFile.getAbsolutePath()
                    );

            if (!copied) {

                return "COPY_FAILED";
            }

            boolean compiled =
                    dockerService.compileJava(
                            containerName
                    );

            if (!compiled) {
                return "COMPILATION_ERROR";
            }

            String output =
                    dockerService.runJava(
                            containerName,
                            input
                    );

            return output;


        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR";
        }

        finally {

            if (containerName != null) {

                try {

                    dockerService.deleteContainer(containerName);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }
    }
}
package com.anshu.codingjudge.executionservice.service.impl;

import com.anshu.codingjudge.executionservice.service.DockerService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DockerServiceImpl implements DockerService {

    @Override
    public String createContainer() {

        try {

            String containerName =
                    "judge-" + UUID.randomUUID().toString().substring(0, 8);

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "docker",
                            "run",
                            "--name",
                            containerName,
                            "-d",
                            "eclipse-temurin:17-jdk",
                            "sleep",
                            "300"
                    );

            Process process = builder.start();

            int exitCode = process.waitFor();

            if(exitCode == 0){

                System.out.println("Container Created : " + containerName);
                return containerName;

            }else{

                System.out.println("Container Creation Failed");

                String error =
                        new String(
                                process.getErrorStream()
                                        .readAllBytes()
                        );

                System.out.println(error);
                return null;
            }

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    @Override
    public boolean copyJavaFile(
            String containerName,
            String javaFilePath) {

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "docker",
                            "cp",
                            javaFilePath,
                            containerName + ":/"
                    );

            Process process = builder.start();

            int exitCode = process.waitFor();

            if(exitCode == 0){

                System.out.println(
                        "Java File Copied Successfully"
                );

                return true;

            }else{

                System.out.println("Copy Failed");

                String error =
                        new String(
                                process.getErrorStream()
                                        .readAllBytes()
                        );

                System.out.println(error);

                return false;
            }

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean compileJava(
            String containerName) {

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "docker",
                            "exec",
                            containerName,
                            "javac",
                            "/Solution.java"
                    );

            Process process = builder.start();

            int exitCode = process.waitFor();

            if(exitCode == 0){

                System.out.println("Compiled Inside Docker");

                return true;

            }

            String error =
                    new String(
                            process.getErrorStream()
                                    .readAllBytes()
                    );

            System.out.println(error);

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    @Override
    public String runJava(
            String containerName,
            String input) {

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "docker",
                            "exec",
                            "-i",
                            containerName,
                            "java",
                            "-cp",
                            "/",
                            "Solution"
                    );

            Process process = builder.start();

            process.getOutputStream().write(input.getBytes());

            process.getOutputStream().flush();

            process.getOutputStream().close();

            process.waitFor();

            String error =
                    new String(
                            process.getErrorStream()
                                    .readAllBytes()
                    );
            if(!error.isBlank()){

                System.out.println(error);

            }

            String output =
                    new String(
                            process.getInputStream()
                                    .readAllBytes()
                    );

            System.out.println(
                    "Program Output : " + output
            );

            return output.trim();

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR";
        }
    }

    @Override
    public boolean deleteContainer(
            String containerName) {

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "docker",
                            "rm",
                            "-f",
                            containerName
                    );

            Process process = builder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {

                System.out.println(
                        "Container Deleted : " + containerName
                );

                return true;
            }

            String error =
                    new String(
                            process.getErrorStream()
                                    .readAllBytes()
                    );

            System.out.println(error);

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}
package br.com.dias.blurrystoremanager.controller.configuration;

import org.junit.ClassRule;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SNS;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Testcontainers
public abstract class DockerConfiguration {

    static final DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:0.11.3");

    @ClassRule
    @Container
    public static LocalStackContainer localStackContainer = new LocalStackContainer(localstackImage)
            .withServices(SQS, SNS);

}

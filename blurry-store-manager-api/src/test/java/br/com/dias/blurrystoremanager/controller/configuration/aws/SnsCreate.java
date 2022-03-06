package br.com.dias.blurrystoremanager.controller.configuration.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static br.com.dias.blurrystoremanager.controller.configuration.DockerConfiguration.localStackContainer;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SNS;

@Configuration
@Profile("test")
public class SnsCreate {
    private static final Logger LOG = LoggerFactory.getLogger(SnsCreate.class);

    private final String productEventsTopic;
    private final AmazonSNS snsClient;

    public SnsCreate() {
        this.snsClient = AmazonSNSClient.builder()
                .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(SNS))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .build();

        CreateTopicRequest createTopicRequest = new CreateTopicRequest("product-events");
        this.productEventsTopic = this.snsClient.createTopic(createTopicRequest).getTopicArn();

        LOG.info("SNS topic ARN: {}", productEventsTopic);
    }

    @Bean
    public AmazonSNS snsClient() {
        return this.snsClient;
    }


    @Bean(name = "productEventsTopic")
    public Topic snsProductEventsTopic() {
        return new Topic().withTopicArn(productEventsTopic);
    }

}

package br.com.dias.blurrystoremanager.controller.configuration.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import static br.com.dias.blurrystoremanager.controller.configuration.DockerConfiguration.localStackContainer;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Configuration
@Profile("test")
public class SqsCreateSubscribe {

    public SqsCreateSubscribe(final AmazonSNS snsClient,
                              @Qualifier("productEventsTopic") final Topic productEventsTopic) {

        final AmazonSQS sqsClient = AmazonSQSClient.builder().
                withEndpointConfiguration(localStackContainer.getEndpointConfiguration(SQS))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .build();

        final String productEventsQueueUrl = sqsClient.createQueue(new CreateQueueRequest("product-events")).getQueueUrl();

        Topics.subscribeQueue(snsClient, sqsClient, productEventsTopic.getTopicArn(), productEventsQueueUrl);
    }
}


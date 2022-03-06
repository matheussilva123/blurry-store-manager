package br.com.dias.blurrystoremanager.service.event;

import br.com.dias.blurrystoremanager.domain.Envelope;
import br.com.dias.blurrystoremanager.domain.Product;
import br.com.dias.blurrystoremanager.domain.event.ProductEvent;
import br.com.dias.blurrystoremanager.enums.EventType;
import br.com.dias.blurrystoremanager.utils.JsonConverter;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductPublisherImpl implements ProductPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(ProductPublisherImpl.class);

    @Autowired
    private AmazonSNS snsClient;
    @Autowired
    private Topic productEventsTopic;
    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    public ProductPublisherImpl(final AmazonSNS snsClient,
                                @Qualifier("productEventsTopic") final Topic productEventsTopic,
                                final JsonConverter jsonConverter) {
        this.snsClient = snsClient;
        this.productEventsTopic = productEventsTopic;
        this.jsonConverter = jsonConverter;
    }

    public ProductPublisherImpl() {
    }

    public void publishProductEvent(final Product product, final EventType eventType) {
        final var productEvent = ProductEvent.builder()
                .productId(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();

        try {
            final var envelope = Envelope.builder()
                    .eventType(eventType)
                    .data(jsonConverter.toJson(productEvent)).build();

            snsClient.publish(productEventsTopic.getTopicArn(), jsonConverter.toJson(envelope));
        } catch (Exception e) {
            LOG.error("Failed to create product event message" + e.getMessage());
        }
    }

}

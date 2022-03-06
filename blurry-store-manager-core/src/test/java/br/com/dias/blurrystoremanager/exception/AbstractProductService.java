package br.com.dias.blurrystoremanager.exception;

import br.com.dias.blurrystoremanager.repository.ProductRepository;
import br.com.dias.blurrystoremanager.service.ProductServiceImpl;
import br.com.dias.blurrystoremanager.service.event.ProductPublisher;
import br.com.dias.blurrystoremanager.service.event.ProductPublisherImpl;
import br.com.dias.blurrystoremanager.utils.JsonConverter;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public abstract class AbstractProductService {

    @Mock
    protected ProductServiceImpl productServiceImpl;

    @Mock
    protected ProductRepository productRepository;

    @Mock
    protected ProductPublisherImpl productPublisher = Mockito.mock(ProductPublisherImpl.class);

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.productServiceImpl = new ProductServiceImpl(productRepository, productPublisher);
    }

}

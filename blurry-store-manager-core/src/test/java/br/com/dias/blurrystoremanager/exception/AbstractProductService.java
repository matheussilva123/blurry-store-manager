package br.com.dias.blurrystoremanager.exception;

import br.com.dias.blurrystoremanager.repository.ProductRepository;
import br.com.dias.blurrystoremanager.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AbstractProductService {

    @Mock
    protected ProductServiceImpl productService;

    @Mock
    protected ProductRepository productRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.productService = new ProductServiceImpl(productRepository);
    }

}

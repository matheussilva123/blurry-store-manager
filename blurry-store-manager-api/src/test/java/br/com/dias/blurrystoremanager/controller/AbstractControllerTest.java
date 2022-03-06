package br.com.dias.blurrystoremanager.controller;

import br.com.dias.blurrystoremanager.controller.configuration.DockerConfiguration;
import br.com.dias.blurrystoremanager.service.ProductServiceImpl;
import br.com.dias.blurrystoremanager.service.event.ProductPublisherImpl;
import br.com.dias.blurrystoremanager.utils.JsonConverter;
import br.com.dias.blurrystoremanager.converter.ProductConverter;
import br.com.dias.blurrystoremanager.domain.Product;
import br.com.dias.blurrystoremanager.dto.ProductDTO;
import br.com.dias.blurrystoremanager.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Testcontainers
public abstract class AbstractControllerTest extends DockerConfiguration {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected JsonConverter jsonConverter;

    @Autowired
    protected ProductRepository productRepository;

    protected ProductDTO createAndSaveProduct(final String name, final Float price) {
        final var product = Product.builder().name(name).price(price).build();
        return ProductConverter.convertToDTO(productRepository.save(product));
    }

}

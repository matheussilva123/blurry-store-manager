package br.com.dias.blurrystoremanager.exception;

import br.com.dias.blurrystoremanager.domain.Product;
import br.com.dias.blurrystoremanager.dto.ProductDTO;
import br.com.dias.blurrystoremanager.enums.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest extends AbstractProductService {

    @Test
    void shouldReturnExceptionAndNotPublisherMessageWhenNameIsNull() {
        Exception exception = Assertions.assertThrows(ProductNotCreatedException.class, () -> {
            ProductDTO.builder().name("").price(10.02F).build();
        });

        Mockito.verifyNoInteractions(productPublisher);

        assertEquals("name property cannot be null", exception.getLocalizedMessage());
    }

    @Test
    void shouldReturnExceptionAndNotPublisherMessageWhenPriceIsNull() {
        Exception exception = Assertions.assertThrows(ProductNotCreatedException.class, () -> {
            ProductDTO.builder().name("Product").price(null).build();
        });

        Mockito.verifyNoInteractions(productPublisher);

        assertEquals("price property cannot be null", exception.getLocalizedMessage());
    }

    @Test
    void shouldReturnProductNotFoundWhenProductIdDoesNotExist() {
        final var productId = UUID.randomUUID();
        final var productDTO = ProductDTO.builder().name("a").price(10F).build();

        Exception exception = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productServiceImpl.updateAndReturnDTO(productId, productDTO);
        });

        assertEquals(String.format("ProductId: %s not found", productId.toString()), exception.getLocalizedMessage());
    }

    @Test
    void shouldReturnProductNotUpdatedExceptionAndNotPublisherMessageWhenProductIdDoesNotExist() {
        final var productDTO = ProductDTO.builder().name("a").price(10F).build();

        Exception exception = Assertions.assertThrows(ProductNotUpdatedException.class, () -> {
            productServiceImpl.updateAndReturnDTO(null, productDTO);
        });

        Mockito.verifyNoInteractions(productPublisher);

        assertEquals("Product not updated, id cannot be null", exception.getLocalizedMessage());
    }

    @Test
    void shouldPublisherAMessageWhenAProductWasCreated() {
        final var product = Product.builder().id(UUID.randomUUID()).name("Test product").price(10F).build();
        final var productDTO = ProductDTO.builder().name("Test product").price(10F).build();

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        productServiceImpl.createAndReturnDTO(productDTO);

        verify(productPublisher).publishProductEvent(product, EventType.PRODUCT_CREATED);
    }

    @Test
    void shouldPublisherAMessageWhenAProductWasUpdated() {
        final var product = Product.builder().id(UUID.randomUUID()).name("Test product").price(10F).build();
        final var productDTO = ProductDTO.builder().name("Test product").price(10F).build();

        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        productServiceImpl.updateAndReturnDTO(product.getId(), productDTO);

        verify(productPublisher).publishProductEvent(product, EventType.PRODUCT_UPDATED);
    }

    @Test
    void shouldReturnAMessageWhenAProductWasUpdated() {
        final var product = Product.builder().id(UUID.randomUUID()).name("Test product").price(10F).build();
        final var productDTO = ProductDTO.builder().name("Test product").price(10F).build();

        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        productServiceImpl.updateAndReturnDTO(product.getId(), productDTO);

        verify(productPublisher).publishProductEvent(product, EventType.PRODUCT_UPDATED);
    }

}

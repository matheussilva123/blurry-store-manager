package br.com.dias.blurrystoremanager.exception;

import br.com.dias.blurrystoremanager.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProductServiceTest extends AbstractProductService {

    @Test
    void shouldReturnExceptionWhenNameIsNull() {
        Exception exception = Assertions.assertThrows(ProductNotCreatedException.class, () -> {
            ProductDTO.builder().name("").price(10.02F).build();
        });

        Assertions.assertEquals("name property cannot be null", exception.getLocalizedMessage());
    }

    @Test
    void shouldReturnExceptionWhenPriceIsNull() {
        Exception exception = Assertions.assertThrows(ProductNotCreatedException.class, () -> {
            ProductDTO.builder().name("Product").price(null).build();
        });

        Assertions.assertEquals("price property cannot be null", exception.getLocalizedMessage());
    }

    @Test
    void shouldReturnProductNotFoundWhenProductIdDoesNotExist() {
        final var productId = UUID.randomUUID();
        final var productDTO = ProductDTO.builder().name("a").price(10F).build();

        Exception exception = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productService.updateAndReturnDTO(productId, productDTO);
        });

        Assertions.assertEquals(String.format("ProductId: %s not found", productId.toString()), exception.getLocalizedMessage());
    }

    @Test
    void shouldReturnProductNotUpdatedExceptionWhenProductIdDoesNotExist() {
        final var productDTO = ProductDTO.builder().name("a").price(10F).build();

        Exception exception = Assertions.assertThrows(ProductNotUpdatedException.class, () -> {
            productService.updateAndReturnDTO(null, productDTO);
        });

        Assertions.assertEquals("Product not updated, id cannot be null", exception.getLocalizedMessage());
    }

}

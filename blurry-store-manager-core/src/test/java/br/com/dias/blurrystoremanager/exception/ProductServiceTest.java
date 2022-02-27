package br.com.dias.blurrystoremanager.exception;

import br.com.dias.blurrystoremanager.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

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

}

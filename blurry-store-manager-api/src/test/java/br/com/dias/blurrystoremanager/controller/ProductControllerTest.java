package br.com.dias.blurrystoremanager.controller;

import br.com.dias.blurrystoremanager.domain.Product;
import br.com.dias.blurrystoremanager.dto.ProductDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;


@Testcontainers
public class ProductControllerTest extends AbstractControllerTest {

    private final static String PRODUCT_BASE_URL = "/api/store/manager/products";

    @Test
    void shouldCreateProduct() throws Exception {
        //given
        final ProductDTO productDTO = ProductDTO.
                builder()
                .name("Product Cool")
                .price(10.10F)
                .build();

        //when
        final String response = mockMvc.perform(post(PRODUCT_BASE_URL + "/create")
                        .characterEncoding("uft-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.toJson(productDTO)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final Product product = jsonConverter.toObject(response, Product.class);

        //then
        assertEquals("Product Cool", product.getName());
        assertEquals(10.10F, product.getPrice());
    }

    @Test
    void shouldNotCreateProductWhenNameIsEmpty() throws Exception {
        //given
        final Map<String, Float> pricesMap = new HashMap<>();
        pricesMap.put("price", 10.10F);

        //then
        final var response = mockMvc.perform(post(PRODUCT_BASE_URL + "/create")
                        .characterEncoding("uft-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.toJson(pricesMap)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void shouldUpdateProduct() throws Exception {
        //given
        final var productId = createAndSaveProduct("Blury Test", 9980.99F).getId();

        final var productDTO = ProductDTO.
                builder()
                .name("Blurry Test")
                .price(9990.99F)
                .build();

        //when
        final String response = mockMvc.perform(put(PRODUCT_BASE_URL + "/update/" + productId.toString())
                        .characterEncoding("uft-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.toJson(productDTO)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final Product product = jsonConverter.toObject(response, Product.class);

        //then
        assertEquals("Blurry Test", product.getName());
        assertEquals(9990.99F, product.getPrice());
    }

    @Test
    void shouldNotUpdateProductWhenNameIsEmpty() throws Exception {
        //given
        final var productId = createAndSaveProduct("Hello", 9980.99F).getId();
        final Map<String, Float> pricesMap = new HashMap<>();
        pricesMap.put("price", 10.10F);

        //then
        final var response = mockMvc.perform(put(PRODUCT_BASE_URL + "/update/" + productId)
                        .characterEncoding("uft-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.toJson(pricesMap)))
                .andExpect(status().isBadRequest());
    }

}

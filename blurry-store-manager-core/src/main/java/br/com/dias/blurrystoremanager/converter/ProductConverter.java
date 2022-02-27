package br.com.dias.blurrystoremanager.converter;

import br.com.dias.blurrystoremanager.domain.Product;
import br.com.dias.blurrystoremanager.dto.ProductDTO;

import java.util.UUID;


public final class ProductConverter {

    public static Product convert(final ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }

    public static Product converter(final UUID productId, final ProductDTO productDTO) {
        return Product.builder()
                .id(productId)
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }

    public static ProductDTO convertToDTO(final Product product) {
        final var productDTO = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        return productDTO;
    }

}

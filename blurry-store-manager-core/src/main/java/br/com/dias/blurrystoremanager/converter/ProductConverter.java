package br.com.dias.blurrystoremanager.converter;

import br.com.dias.blurrystoremanager.domain.Product;
import br.com.dias.blurrystoremanager.dto.ProductDTO;


public final class ProductConverter {

    public static Product convert(final ProductDTO productDTO) {
        final var product = new Product(productDTO.getName(), productDTO.getPrice());
        return product;
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

package br.com.dias.blurrystoremanager.service;

import br.com.dias.blurrystoremanager.dto.ProductDTO;

import java.util.UUID;

public interface ProductService {

    ProductDTO createAndReturnDTO(ProductDTO productDTO);

    ProductDTO updateAndReturnDTO(UUID productId, ProductDTO productDTO);

    Boolean existsById(UUID productId);

}
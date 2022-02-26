package br.com.dias.blurrystoremanager.service;

import br.com.dias.blurrystoremanager.converter.ProductConverter;
import br.com.dias.blurrystoremanager.dto.ProductDTO;
import br.com.dias.blurrystoremanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public final class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createAndReturnDTO(final ProductDTO productDTO) {
            final var product = productRepository.save(ProductConverter.convert(productDTO));
            return ProductConverter.convertToDTO(product);
    }
}

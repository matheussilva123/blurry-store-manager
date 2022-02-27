package br.com.dias.blurrystoremanager.service;

import br.com.dias.blurrystoremanager.converter.ProductConverter;
import br.com.dias.blurrystoremanager.dto.ProductDTO;
import br.com.dias.blurrystoremanager.exception.ProductNotFoundException;
import br.com.dias.blurrystoremanager.exception.ProductNotUpdatedException;
import br.com.dias.blurrystoremanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createAndReturnDTO(final ProductDTO productDTO) {
        final var product = productRepository.save(ProductConverter.convert(productDTO));
        return ProductConverter.convertToDTO(product);
    }

    @Override
    public ProductDTO updateAndReturnDTO(final UUID productId, final ProductDTO productDTO) {
        if (productId != null) {
            if (existsById(productId)) {
                final var product = productRepository.save(ProductConverter.converter(productId, productDTO));
                return ProductConverter.convertToDTO(product);
            }
        }
        throw new ProductNotUpdatedException("Product not updated, id cannot be null");
    }

    @Override
    public Boolean existsById(UUID productId) {
        final Boolean resultId = productRepository.existsById(productId);
        if (resultId) {
            return resultId;
        }
        throw new ProductNotFoundException(String.format("ProductId: %s not found", productId.toString()));
    }

}

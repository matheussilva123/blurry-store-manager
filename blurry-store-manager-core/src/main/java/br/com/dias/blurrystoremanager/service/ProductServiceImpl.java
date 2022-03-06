package br.com.dias.blurrystoremanager.service;

import br.com.dias.blurrystoremanager.converter.ProductConverter;
import br.com.dias.blurrystoremanager.dto.ProductDTO;
import br.com.dias.blurrystoremanager.enums.EventType;
import br.com.dias.blurrystoremanager.exception.ProductNotFoundException;
import br.com.dias.blurrystoremanager.exception.ProductNotUpdatedException;
import br.com.dias.blurrystoremanager.repository.ProductRepository;
import br.com.dias.blurrystoremanager.service.event.ProductPublisherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductPublisherImpl productPublisher;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository, ProductPublisherImpl productPublisher) {
        this.productRepository = productRepository;
        this.productPublisher = productPublisher;
    }

    @Override
    public ProductDTO createAndReturnDTO(final ProductDTO productDTO) {
        final var product = productRepository.save(ProductConverter.convert(productDTO));
        productPublisher.publishProductEvent(product, EventType.PRODUCT_CREATED);
        return ProductConverter.convertToDTO(product);
    }

    @Override
    public ProductDTO updateAndReturnDTO(final UUID productId, final ProductDTO productDTO) {
        if (productId != null) {
            if (existsById(productId)) {
                final var product = productRepository.save(ProductConverter.converter(productId, productDTO));
                productPublisher.publishProductEvent(product, EventType.PRODUCT_UPDATED);
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

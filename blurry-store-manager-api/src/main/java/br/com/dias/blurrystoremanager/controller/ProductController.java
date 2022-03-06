package br.com.dias.blurrystoremanager.controller;

import br.com.dias.blurrystoremanager.dto.ProductDTO;
import br.com.dias.blurrystoremanager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/store/manager/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ProductDTO create(@RequestBody final ProductDTO productDTO) {
        return productService.createAndReturnDTO(productDTO);
    }

    @PutMapping("/update/{productId}")
    public ProductDTO update(
            @PathVariable final UUID productId,
            @RequestBody final ProductDTO productDTO) {
        return productService.updateAndReturnDTO(productId, productDTO);
    }

}

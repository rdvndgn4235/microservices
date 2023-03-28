package com.rd.productservice.service;

import com.rd.productservice.dto.ProductRequest;
import com.rd.productservice.dto.ProductResponse;
import com.rd.productservice.model.Product;
import com.rd.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created at 23.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                                 .name(productRequest.getName())
                                 .price(productRequest.getPrice())
                                 .description(productRequest.getDescription())
                                 .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                                .map(this::productToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                              .id(product.getId())
                              .name(product.getName())
                              .price(product.getPrice())
                              .description(product.getDescription())
                              .build();
    }
}

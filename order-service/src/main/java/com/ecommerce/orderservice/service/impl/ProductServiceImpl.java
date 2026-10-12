package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.dto.response.ProductResponseDTO;
import com.ecommerce.orderservice.entity.ProductEntity;
import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.repository.ProductRepository;
import com.ecommerce.orderservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        // Map Entity to DTO - only expose safe fields (id, name, sellPrice)
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sellPrice(product.getSellPrice())
                .build();
    }
}

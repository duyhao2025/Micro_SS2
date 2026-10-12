package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.response.ProductResponseDTO;

public interface ProductService {

    ProductResponseDTO getProductById(Long id);
}

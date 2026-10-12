package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.response.BaseResponse;
import com.ecommerce.orderservice.dto.response.ProductResponseDTO;
import com.ecommerce.orderservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Bài 2: Get product by ID - returns ProductResponseDTO (only id, name, sellPrice)
     * Sensitive fields like importPrice, sku, stockQuantity are hidden
     * GET /api/v1/products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductResponseDTO>> getProductById(@PathVariable Long id) {
        ProductResponseDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(BaseResponse.success("Product retrieved successfully", productDTO));
    }
}

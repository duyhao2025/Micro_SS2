package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.request.OrderRequestDTO;
import com.ecommerce.orderservice.dto.response.BaseResponse;
import com.ecommerce.orderservice.dto.response.OrderDTO;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Bài 1: Health check endpoint
     * GET /api/v1/orders/health-check
     */
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Order Service is Up");
    }

    /**
     * Bài 4: Get order by ID (throws ResourceNotFoundException if not found)
     * GET /api/v1/orders/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<OrderDTO>> getOrderById(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(BaseResponse.success("Order retrieved successfully", orderDTO));
    }

    /**
     * Bài 5: Create new order (E-learning)
     * POST /api/v1/orders
     */
    @PostMapping
    public ResponseEntity<BaseResponse<OrderDTO>> createOrder(@Valid @RequestBody OrderRequestDTO requestDTO) {
        OrderDTO orderDTO = orderService.createOrder(requestDTO);
        return new ResponseEntity<>(
                BaseResponse.created("Order created successfully", orderDTO),
                HttpStatus.CREATED
        );
    }
}

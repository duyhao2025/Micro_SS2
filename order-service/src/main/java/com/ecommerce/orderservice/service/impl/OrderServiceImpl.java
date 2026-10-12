package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.dto.request.OrderRequestDTO;
import com.ecommerce.orderservice.dto.response.OrderDTO;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // Simulated course data (in real scenario, this would call Course-Service via REST/Feign)
    private static final List<Long> AVAILABLE_COURSE_IDS = Arrays.asList(1L, 2L, 3L, 4L, 5L);
    private static final BigDecimal COURSE_PRICE = new BigDecimal("299.99");

    @Override
    public OrderDTO createOrder(OrderRequestDTO requestDTO) {
        // Business logic: Check if the course exists
        if (!AVAILABLE_COURSE_IDS.contains(requestDTO.getCourseId())) {
            throw new ResourceNotFoundException("Course", "id", requestDTO.getCourseId());
        }

        // Business logic: Calculate total amount
        BigDecimal totalAmount = COURSE_PRICE;

        // Build and save the Order entity
        Order order = Order.builder()
                .studentId(requestDTO.getStudentId())
                .courseId(requestDTO.getCourseId())
                .totalAmount(totalAmount)
                .build();

        Order savedOrder = orderRepository.save(order);

        // Map entity to DTO
        return mapToDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));

        return mapToDTO(order);
    }

    private OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .studentId(order.getStudentId())
                .courseId(order.getCourseId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}

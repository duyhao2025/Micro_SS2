package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.request.OrderRequestDTO;
import com.ecommerce.orderservice.dto.response.OrderDTO;

public interface OrderService {

    OrderDTO createOrder(OrderRequestDTO requestDTO);

    OrderDTO getOrderById(Long id);
}

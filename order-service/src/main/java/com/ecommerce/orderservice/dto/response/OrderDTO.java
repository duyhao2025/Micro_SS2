package com.ecommerce.orderservice.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;
    private Long studentId;
    private Long courseId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
}

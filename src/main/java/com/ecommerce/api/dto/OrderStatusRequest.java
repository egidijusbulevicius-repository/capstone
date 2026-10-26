package com.ecommerce.api.dto;

import com.ecommerce.api.entity.Order;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusRequest {

    @NotNull(message = "Status is required")
    private Order.OrderStatus status;
}

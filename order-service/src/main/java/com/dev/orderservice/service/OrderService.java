package com.dev.orderservice.service;

import com.dev.orderservice.dto.OrderDto;
import com.dev.orderservice.dto.OrderRequest;
import com.dev.orderservice.dto.OrderResponse;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(String orderNumber);
}

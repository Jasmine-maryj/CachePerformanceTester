package com.dev.orderservice.service;

import com.dev.orderservice.dto.OrderDto;
import com.dev.orderservice.dto.OrderResponse;
import com.dev.orderservice.dto.OrderStatus;
import com.dev.orderservice.dto.OrderRequest;
import com.dev.orderservice.entity.Order;
import com.dev.orderservice.handler.CustomException;
import com.dev.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String order_number = UUID.randomUUID().toString();
        order.setOrderNumber(order_number);
        order.setName(orderRequest.getName());
        order.setPrice(orderRequest.getPrice());
        order.setQuantity(orderRequest.getQuantity());
        order.setOrderStatus(OrderStatus.CREATED);
        orderRepository.save(order);
        return order_number;
    }

    @Override
    public OrderResponse getOrderDetails(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if(order != null){
         return OrderResponse.builder()
                .name(order.getName())
                .price(order.getPrice())
                .orderStatus(order.getOrderStatus())
                .quantity(order.getQuantity())
                .build();
        }else {
            throw new CustomException("Order not Fond");
        }
    }
}

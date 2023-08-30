package com.dev.orderservice.controller;

import com.dev.orderservice.dto.OrderDto;
import com.dev.orderservice.dto.OrderRequest;
import com.dev.orderservice.dto.OrderResponse;
import com.dev.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
        String order = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("orderNumber") String orderNumber){
        OrderResponse orderResponse = orderService.getOrderDetails(orderNumber);
        return ResponseEntity.ok(orderResponse);
    }
}

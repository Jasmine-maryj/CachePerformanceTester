package com.dev.orderservice.entity;

import com.dev.orderservice.dto.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String orderNumber;
    private String name;
    private double price;
    private long quantity;
    private OrderStatus orderStatus;
}

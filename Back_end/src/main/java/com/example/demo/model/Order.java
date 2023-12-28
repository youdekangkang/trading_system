package com.example.demo.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long userId;
    private String stockSymbol;
    private String orderType; // BUY Or SELL
    private Integer quantity;
    private BigDecimal price;
    private Timestamp orderTime;
    private String status;  // OPEN, CLOSED, CANCELLED
}

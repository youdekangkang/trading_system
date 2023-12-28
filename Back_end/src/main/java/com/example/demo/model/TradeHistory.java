package com.example.demo.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "trade_history")
@Data
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;
    private Long orderId;
    private Long userId;
    private String stockSymbol;
    private String tradeType;   // BUY Or SELL
    private Integer quantity;
    private BigDecimal price;
    private Timestamp tradeTime;
}

package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stocks")
@Data               // 自动生成 getter、setter、equals、hashCode 和 toString 方法
@NoArgsConstructor  // 自动生成无参数的构造函数
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false, unique = true)
    private String stockSymbol;

    private BigDecimal currentPrice;
    private BigDecimal peRatio;
    private Long volume;
    private BigDecimal marketCap;
    private BigDecimal week52High;
    private BigDecimal week52Low;

    private LocalDateTime updatedAt;

    // 预留字段
    private String reservedText;
    private BigDecimal reservedNumber;
}

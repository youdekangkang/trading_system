package com.example.demo.model;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "market_info")
@Data
public class MarketInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketId;
    private Time openTime;
    private Time closeTime;
    private Long totalVolume;
    private String reservedText;
    private Double reservedNumber;
}

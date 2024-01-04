package com.example.demo.repository;

import com.example.demo.model.StockPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, Long> {

    @Query("SELECT AVG(sph.price) FROM StockPriceHistory sph WHERE sph.stockSymbol = :stockSymbol")
    BigDecimal findAveragePriceByStockSymbol(String stockSymbol);
}

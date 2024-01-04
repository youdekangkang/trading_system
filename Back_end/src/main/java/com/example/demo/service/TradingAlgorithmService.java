package com.example.demo.service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockPriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TradingAlgorithmService {


    @Autowired
    private StockPriceHistoryRepository stockPriceHistoryRepository;
    public Decision makeDecision(Stock stock) {
        BigDecimal meanPrice = calculateMeanPrice(stock);  // 计算历史平均价格
        BigDecimal currentPrice = stock.getCurrentPrice();

        if (currentPrice.compareTo(meanPrice) > 0) {
            return Decision.SELL;  // 如果当前价格高于平均价格，考虑卖出
        } else if (currentPrice.compareTo(meanPrice) < 0) {
            return Decision.BUY;  // 如果当前价格低于平均价格，考虑买入
        }
        return Decision.HOLD;  // 如果价格接近平均价格，保持不变
    }

    private BigDecimal calculateMeanPrice(Stock stock) {
        // 查询数据库获取股票的历史平均价格
        return stockPriceHistoryRepository.findAveragePriceByStockSymbol(stock.getStockSymbol());
    }
}

enum Decision {
    BUY, SELL, HOLD
}

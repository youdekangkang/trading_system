package com.example.demo.service;

import com.example.demo.model.TradeHistory;
import com.example.demo.repository.TradeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TradeHistoryService {
    @Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    public List<TradeHistory> getUserTradeHistory(Long userId) {
        // 逻辑来获取用户的交易历史记录
        return tradeHistoryRepository.findByUserId(userId);
    }

    // 其他可能的方法
}

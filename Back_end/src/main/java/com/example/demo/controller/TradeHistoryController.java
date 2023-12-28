package com.example.demo.controller;

import com.example.demo.ApiResponse;
import com.example.demo.service.TradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tradeHistory")
public class TradeHistoryController {
    @Autowired
    private TradeHistoryService tradeHistoryService;

    @GetMapping("/{userId}")
    public ApiResponse getUserTradeHistory(@PathVariable Long userId) {
        // 调用Service层方法获取用户交易历史
        return new ApiResponse(true, "User trade history");
    }
}

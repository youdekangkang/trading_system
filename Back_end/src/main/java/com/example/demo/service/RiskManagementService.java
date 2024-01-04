package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RiskManagementService {

    public boolean checkRisk(Order order, User user) {
        // 验证订单金额不超过用户账户余额的一定百分比
        BigDecimal maxTradeAmount = user.getAccountBalance().multiply(new BigDecimal("0.1"));  // 用户只能交易其余额的10%
        return order.getPrice().multiply(new BigDecimal(order.getQuantity())).compareTo(maxTradeAmount) <= 0;
    }
}
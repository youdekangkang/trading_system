package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.TradeHistory;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.TradeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    public Order placeOrder(Order newOrder) {
        newOrder.setStatus("OPEN");
        return orderRepository.save(newOrder);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && "OPEN".equals(order.getStatus())) {
            order.setStatus("CANCELLED");
            orderRepository.save(order);
        }
    }

    // 此方法应当由交易引擎调用
    @Transactional
    public void processTrade(Order order, Integer quantity, BigDecimal price) {
        if (order != null && "OPEN".equals(order.getStatus())) {
            TradeHistory trade = new TradeHistory();
            trade.setOrderId(order.getOrderId());
            trade.setUserId(order.getUserId());
            trade.setStockSymbol(order.getStockSymbol());
            trade.setTradeType(order.getOrderType());
            trade.setQuantity(quantity);
            trade.setPrice(price);
            tradeHistoryRepository.save(trade);

            if (quantity >= order.getQuantity()) {
                order.setStatus("CLOSED");
            }
            orderRepository.save(order);
        }
    }
}



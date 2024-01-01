package com.example.demo.listeners;

import com.example.demo.events.OrderEvent;
import com.example.demo.model.Order;
import com.example.demo.model.TradeHistory;
import com.example.demo.repository.TradeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    @Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    @EventListener
    public void onOrderEvent(OrderEvent event) {
        Order order = event.getOrder();

        // 假设每个订单事件都对应一次交易
        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setOrderId(order.getOrderId());
        tradeHistory.setUserId(order.getUserId());
        tradeHistory.setStockSymbol(order.getStockSymbol());
        tradeHistory.setTradeType(order.getOrderType());
        tradeHistory.setQuantity(order.getQuantity());
        tradeHistory.setPrice(order.getPrice());

        tradeHistoryRepository.save(tradeHistory);
    }
}

package com.example.demo.events;

import com.example.demo.model.Order;
import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    private Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}

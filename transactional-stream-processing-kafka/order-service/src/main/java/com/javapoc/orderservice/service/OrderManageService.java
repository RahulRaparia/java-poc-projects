package com.javapoc.orderservice.service;

import com.base.orderservicemodels.Order;
import com.base.orderservicemodels.OrderServiceConstants;
import org.springframework.stereotype.Service;

@Service
public class OrderManageService {
    public Order confirm(Order orderPayment, Order orderStock) {
        Order order = new Order(orderPayment.getId(),
                orderPayment.getCustomerId(),
                orderPayment.getProductId(),
                orderPayment.getProductCount(),
                orderPayment.getPrice()
                );

        if (orderPayment.getStatus().equals(OrderServiceConstants.ORDER_STATUS_ACCEPTED) &&
                orderStock.getStatus().equals(OrderServiceConstants.ORDER_STATUS_ACCEPTED)) {
            order.setStatus(OrderServiceConstants.ORDER_STATUS_CONFIRMED);
        } else if (orderPayment.getStatus().equals(OrderServiceConstants.ORDER_STATUS_REJECTED) &&
                orderStock.getStatus().equals(OrderServiceConstants.ORDER_STATUS_REJECTED)) {
            order.setStatus(OrderServiceConstants.ORDER_STATUS_REJECTED);
        } else if (orderPayment.getStatus().equals(OrderServiceConstants.ORDER_STATUS_REJECTED) ||
                    orderStock.getStatus().equals(OrderServiceConstants.ORDER_STATUS_REJECTED)) {
            String source = orderPayment.getStatus().equals(OrderServiceConstants.ORDER_STATUS_REJECTED) ?
                    OrderServiceConstants.ORDER_SOURCE_PAYMENT : OrderServiceConstants.ORDER_SOURCE_STOCK;
            order.setStatus(OrderServiceConstants.ORDER_STATUS_ROLLBACK);
            order.setSource(source);
        }
        return order;
    }
}

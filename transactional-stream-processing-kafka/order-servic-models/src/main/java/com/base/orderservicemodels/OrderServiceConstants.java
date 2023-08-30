package com.base.orderservicemodels;

public class OrderServiceConstants {
    // Kafka Topics
    public static String ORDER_TOPIC = "orders";
    public static String ORDER_PAYMENT_TOPIC = "payment-orders";
    public static String ORDER_STOCK_TOPIC = "stock-orders";


    // Order Status
    public static String ORDER_STATUS_ACCEPTED = "ACCEPT";
    public static String ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public static String ORDER_STATUS_REJECTED = "REJECT";
    public static String ORDER_STATUS_ROLLBACK = "ROLLBACK";

    // Order Source
    public static String ORDER_SOURCE_PAYMENT = "PAYMENT";
    public static String ORDER_SOURCE_STOCK = "STOCK";
}

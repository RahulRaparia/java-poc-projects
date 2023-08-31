package com.javapoc.paymentservice;


//import com.javapoc.orderservice.service.OrderManageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableKafka
public class PaymentServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceApplication.class);




	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Autowired
	private OrderManageService orderManageService;
//
//	@KafkaListener(id = "orders", topics = "orders", groupId = "payment")
//	public void onEvent(Order o) {
//		LOG.info("Received order: {}", o);
////		if (o.getStatus().equals(OrderServiceConstants.ORDER_STATUS_NEW)) {
////			o.setStatus(OrderServiceConstants.ORDER_STATUS_ACCEPTED);
////		}
//	}
}

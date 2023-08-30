package com.javapoc.orderservice;

import com.base.orderservicemodels.Order;
import com.base.orderservicemodels.OrderServiceConstants;
import com.javapoc.orderservice.service.OrderManageService;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Duration;

@SpringBootApplication
@EnableKafkaStreams
@EnableAsync
public class OrderServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public NewTopic orders() {
		return TopicBuilder.name(OrderServiceConstants.ORDER_TOPIC)
				.partitions(3)
				.compact()
				.build();
	}
//
	@Bean
	public NewTopic paymentOrders() {
		return TopicBuilder.name(OrderServiceConstants.ORDER_PAYMENT_TOPIC)
				.partitions(3)
				.compact()
				.build();
	}
//
	@Bean
	public NewTopic stockOrders() {
		return TopicBuilder.name(OrderServiceConstants.ORDER_STOCK_TOPIC)
				.partitions(3)
				.compact()
				.build();
	}
//

	@Autowired
	OrderManageService orderManageService;
//
	@Bean
	public KStream<Long, Order> stream(StreamsBuilder builder) {
		JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);
		KStream<Long, Order> stream = builder
				.stream(OrderServiceConstants.ORDER_PAYMENT_TOPIC,
						Consumed.with(Serdes.Long(), orderSerde));
		stream.join(
				builder.stream(OrderServiceConstants.ORDER_STOCK_TOPIC),
						orderManageService::confirm,
						JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(10)),
						StreamJoined.with(Serdes.Long(), orderSerde, orderSerde))
				.peek((k, o) -> LOG.info("Order Confirmed : " + o))
				.to(OrderServiceConstants.ORDER_TOPIC);
		return stream;
	}

}

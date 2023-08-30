# order-service

The order-service is the most important microservice in our scenario. It acts as an order gateway and a saga pattern orchestrator. It requires all the three topics used in our architecture. 

## Annotaions :

### @EnableKafkaStreams
The `@EnableKafkaStreams` annotation is used to enable Kafka Streams in a Spring Boot application. This annotation is used in the `OrderServiceApplication` class.
The `@EnableKafkaStreams` annotation is used to autoconfigure and enable the Kafka Streams default components in a Spring Boot application. This annotation is typically used on a configuration class, and it automatically imports the default Kafka Streams configuration class `KafkaStreamsDefaultConfiguration`. This class registers a `StreamsBuilderFactoryBean` using the `KafkaStreamsConfiguration` class.

### @EnableAsync

The `@EnableAsync` annotation is used to enable Spring's asynchronous method execution capability. This annotation is typically used on a configuration class, and it enables annotation-driven async processing for an entire Spring application context. By default, Spring will search for an associated thread pool definition, either a unique `TaskExecutor` bean in the context or an `Executor` bean named "taskExecutor". If neither of these is resolvable, a `SimpleAsyncTaskExecutor` will be used to process async method invocations.
## Auto Create Topics
In order to automatically create topics on application startup we need to define the following beans in out `OrderServiceApplication.class`:

``` java
@Bean
public NewTopic orders() {
    return TopicBuilder.name("orders")
        .partitions(3)
        .compact()
        .build();
}

@Bean
public NewTopic paymentTopic() {
    return TopicBuilder.name("payment-orders")
        .partitions(3)
        .compact()
        .build();
}

@Bean
public NewTopic stockTopic() {
    return TopicBuilder.name("stock-orders")
        .partitions(3)
        .compact()
        .build();  
```
The code defines three beans, `orders`, `paymentTopic`, and `stockTopic`, which are instances of the `NewTopic` class. These beans represent Kafka topics with the names "orders", "payment-orders", and "stock-orders" respectively.

The `TopicBuilder` class is used to create instances of the `NewTopic` class. The `name` method is used to set the name of the topic, while the `partitions` method is used to set the number of partitions for the topic. The `compact` method sets the cleanup policy for the topic to "compact", which means that Kafka will try to retain at least the last known value for each message key within the log of data for a single topic partition. Finally, the `build` method is called to create an instance of the `NewTopic` class.

Each of these topics has 3 partitions, as specified by the `.partitions(3)` method call. Partitions allow for parallelism in data consumption from a topic by splitting the data in a topic across multiple brokers. This can improve scalability and performance.

In summary, this code snippet creates three Kafka topics named "orders", "payment-orders", and "stock-orders" with 3 partitions each and a cleanup policy of "compact". These topics are represented as beans in the Spring Boot application context.

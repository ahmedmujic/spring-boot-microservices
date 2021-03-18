package com.codecta.emailservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class ConsumerConfiguration {

    public static final String TOPIC_EXCHANGE_NAME = "spring-amqp-app.eventExchange";
    public static final String ORDERS_QUEUE_NAME = "spring-amqp-app.playerEmailQueue";
    public static final String ORDERS_ROUTING_KEY = "player.*";
    @Bean
    public TopicExchange eventExchange() {

        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {

        return new Queue(ORDERS_QUEUE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange eventExchange) {

        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with(ORDERS_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

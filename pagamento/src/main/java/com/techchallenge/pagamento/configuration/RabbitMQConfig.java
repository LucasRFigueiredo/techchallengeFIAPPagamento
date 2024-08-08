package com.techchallenge.pagamento.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CHECKOUT_QUEUE = "checkoutQueue";
    public static final String PAYMENT_EXCHANGE = "paymentExchange";
    public static final String PAYMENT_APPROVED_ROUTING_KEY = "paymentApproved";
    public static final String PAYMENT_REJECTED_ROUTING_KEY = "paymentRejected";
    public static final String NOTIFICATION_QUEUE = "notificationQueue";
    public static final String KITCHEN_QUEUE = "kitchenQueue";

    @Bean
    public Queue checkoutQueue() {
        return new Queue(CHECKOUT_QUEUE, true);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE, true);
    }

    @Bean
    public Queue kitchenQueue() {
        return new Queue(KITCHEN_QUEUE, true);
    }

    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(PAYMENT_EXCHANGE);
    }

    @Bean
    public Binding checkoutQueueBinding(Queue checkoutQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(checkoutQueue).to(paymentExchange).with(CHECKOUT_QUEUE);
    }

    @Bean
    public Binding paymentApprovedBinding(DirectExchange paymentExchange) {
        return BindingBuilder.bind(new Queue(PAYMENT_APPROVED_ROUTING_KEY, true))
                .to(paymentExchange).with(PAYMENT_APPROVED_ROUTING_KEY);
    }

    @Bean
    public Binding paymentRejectedBinding(DirectExchange paymentExchange) {
        return BindingBuilder.bind(new Queue(PAYMENT_REJECTED_ROUTING_KEY, true))
                .to(paymentExchange).with(PAYMENT_REJECTED_ROUTING_KEY);
    }

    @Bean
    public Binding notificationQueueBinding(Queue notificationQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(notificationQueue).to(paymentExchange).with(NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding kitchenQueueBinding(Queue kitchenQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(kitchenQueue).to(paymentExchange).with(KITCHEN_QUEUE);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

package com.techchallenge.pagamento.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CHECKOUT_QUEUE = "checkoutQueue";
    public static final String PAYMENT_EXCHANGE = "paymentExchange";
    public static final String PAYMENT_APPROVED_ROUTING_KEY = "paymentApproved";
    public static final String PAYMENT_REJECTED_ROUTING_KEY = "paymentRejected";

    @Bean
    public Queue checkoutQueue() {
        return new Queue(CHECKOUT_QUEUE, true);
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
}

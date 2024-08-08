package com.techchallenge.producao.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PRODUCT_EXCHANGE = "productExchange";
    public static final String PRODUCT_CREATED_QUEUE = "productCreatedQueue";
    public static final String PRODUCT_UPDATED_QUEUE = "productUpdatedQueue";
    public static final String PRODUCT_DELETED_QUEUE = "productDeletedQueue";
    public static final String ORDER_EXCHANGE = "orderExchange";
    public static final String ORDER_CREATED_QUEUE = "orderCreatedQueue";
    public static final String ORDER_UPDATED_QUEUE = "orderUpdatedQueue";
    public static final String ORDER_DELETED_QUEUE = "orderDeletedQueue";

    @Bean
    DirectExchange productExchange() {
        return new DirectExchange(PRODUCT_EXCHANGE);
    }

    @Bean
    DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean
    Queue productCreatedQueue() {
        return new Queue(PRODUCT_CREATED_QUEUE, true);
    }

    @Bean
    Queue productUpdatedQueue() {
        return new Queue(PRODUCT_UPDATED_QUEUE, true);
    }

    @Bean
    Queue productDeletedQueue() {
        return new Queue(PRODUCT_DELETED_QUEUE, true);
    }

    @Bean
    Queue orderCreatedQueue() {
        return new Queue(ORDER_CREATED_QUEUE, true);
    }

    @Bean
    Queue orderUpdatedQueue() {
        return new Queue(ORDER_UPDATED_QUEUE, true);
    }

    @Bean
    Queue orderDeletedQueue() {
        return new Queue(ORDER_DELETED_QUEUE, true);
    }

    @Bean
    Binding productCreatedBinding(DirectExchange productExchange, Queue productCreatedQueue) {
        return BindingBuilder.bind(productCreatedQueue).to(productExchange).with("product.created");
    }

    @Bean
    Binding productUpdatedBinding(DirectExchange productExchange, Queue productUpdatedQueue) {
        return BindingBuilder.bind(productUpdatedQueue).to(productExchange).with("product.updated");
    }

    @Bean
    Binding productDeletedBinding(DirectExchange productExchange, Queue productDeletedQueue) {
        return BindingBuilder.bind(productDeletedQueue).to(productExchange).with("product.deleted");
    }

    @Bean
    Binding orderCreatedBinding(DirectExchange orderExchange, Queue orderCreatedQueue) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with("order.created");
    }

    @Bean
    Binding orderUpdatedBinding(DirectExchange orderExchange, Queue orderUpdatedQueue) {
        return BindingBuilder.bind(orderUpdatedQueue).to(orderExchange).with("order.updated");
    }

    @Bean
    Binding orderDeletedBinding(DirectExchange orderExchange, Queue orderDeletedQueue) {
        return BindingBuilder.bind(orderDeletedQueue).to(orderExchange).with("order.deleted");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}

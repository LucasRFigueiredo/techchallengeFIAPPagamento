package com.techchallenge.pagamento.application.events;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public PaymentService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishPaymentApprovedEvent(CheckoutDTO checkoutDTO) {
        amqpTemplate.convertAndSend("paymentExchange", "paymentApproved", checkoutDTO);
    }

    public void publishPaymentRejectedEvent(CheckoutDTO checkoutDTO) {
        amqpTemplate.convertAndSend("paymentExchange", "paymentRejected", checkoutDTO);
    }
}

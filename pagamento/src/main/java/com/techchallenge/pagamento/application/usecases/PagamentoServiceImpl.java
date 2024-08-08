package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.application.events.PaymentService;
import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutMapper;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl {

    private final CheckoutUseCase checkoutUseCase;
    private final PaymentService paymentService;
    private final CheckoutMapper checkoutMapper;

    public PagamentoServiceImpl(CheckoutUseCase checkoutUseCase, PaymentService paymentService, CheckoutMapper checkoutMapper) {
        this.checkoutUseCase = checkoutUseCase;
        this.paymentService = paymentService;
        this.checkoutMapper = checkoutMapper;
    }

    public void aprovar(Long id) {
        Checkout checkout = checkoutUseCase.buscar(id);
        if (checkout != null) {
            checkout.setPagamento("Aprovado");
            checkoutUseCase.atualizarStatus(checkout);
            CheckoutDTO checkoutDTO = checkoutMapper.checkoutToCheckoutDTO(checkout);
            paymentService.publishPaymentApprovedEvent(checkoutDTO);
        }
    }

    public void reprovar(Long id) {
        Checkout checkout = checkoutUseCase.buscar(id);
        if (checkout != null) {
            checkout.setPagamento("Reprovado");
            checkoutUseCase.atualizarStatus(checkout);
            CheckoutDTO checkoutDTO = checkoutMapper.checkoutToCheckoutDTO(checkout);
            paymentService.publishPaymentRejectedEvent(checkoutDTO);
        }
    }
}

package com.techchallenge.pagamento.infrastructure.scheduler;

import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final CheckoutUseCase checkoutUseCase;

    public Scheduler(CheckoutUseCase checkoutUseCase) {
        this.checkoutUseCase = checkoutUseCase;
    }

    public void iniciarTarefaAtualizacaoStatus() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Executando tarefa de atualização de status...");
            atualizarStatus();
        }, 0, 1, TimeUnit.MINUTES);
    }

    private void atualizarStatus() {
        List<Checkout> checkoutsAprovados = checkoutUseCase.buscarPorStatusPagamento("Aprovado");
        for (Checkout checkout : checkoutsAprovados) {
            String statusAtual = checkout.getStatus();
            if ("Em preparação".equals(statusAtual)) {
                checkout.setStatus("Pronto");
            } else if ("Aguardando pagamento".equals(statusAtual)) {
                checkout.setStatus("Em preparação");
            }
            checkoutUseCase.atualizarStatus(checkout);
        }
    }
}

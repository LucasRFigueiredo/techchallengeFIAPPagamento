package com.techchallenge.producao.infrastructure.gateways;

import com.techchallenge.producao.application.dto.PedidoDTO;
import org.springframework.web.reactive.function.client.WebClient;

public class CheckoutWebClient {

    private final WebClient webClient;

    public CheckoutWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/pagamento").build();
    }

    public PedidoDTO criaCheckout(PedidoDTO pedidoDTO) {
        return this.webClient.post()
                .uri("/checkout")
                .bodyValue(pedidoDTO)
                .retrieve()
                .bodyToMono(PedidoDTO.class)
                .block();
    }
}

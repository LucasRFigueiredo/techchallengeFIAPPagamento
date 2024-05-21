package com.techchallenge.producao.infrastructure.gateways;

import com.techchallenge.producao.application.dto.ClienteDTO;
import org.springframework.web.reactive.function.client.WebClient;

public class ClienteWebClient {

    private final WebClient webClient;

    public ClienteWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public ClienteDTO getClienteByCPF(String cpf) {
        return this.webClient.get()
                .uri("/clientes/{cpf}", cpf)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block();
    }
}


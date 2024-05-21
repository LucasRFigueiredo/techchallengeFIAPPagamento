package com.techchallenge.producao.configuration;

import com.techchallenge.producao.application.gateways.pedido.CriarPedidoUseCase;
import com.techchallenge.producao.application.gateways.pedido.ListarPedidoUseCase;
import com.techchallenge.producao.application.gateways.produto.*;
import com.techchallenge.producao.application.usecases.PedidoServiceImpl;
import com.techchallenge.producao.application.usecases.ProdutoServiceImpl;
import com.techchallenge.producao.infrastructure.gateways.CheckoutWebClient;
import com.techchallenge.producao.infrastructure.gateways.ClienteWebClient;
import com.techchallenge.producao.infrastructure.gateways.PedidoRepositoryGateway;
import com.techchallenge.producao.infrastructure.gateways.ProdutoRepositoryGateway;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoMapper;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;
import com.techchallenge.producao.infrastructure.persistence.repository.pedido.SpringPedidoRepository;
import com.techchallenge.producao.infrastructure.persistence.repository.produto.SpringProdutoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan(basePackages = {
        "com.techchallenge.producao.application",
        "com.techchallenge.producao.domain",
        "com.techchallenge.producao.infrastructure.gateways",
        "com.techchallenge.producao.infrastructure.mapper",
        "com.techchallenge.producao.infrastructure.persistence.repository",
        "com.techchallenge.producao.infrastructure.controllers"
})
@EntityScan(basePackages = "com.techchallenge.producao.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.techchallenge.producao.infrastructure.persistence.repository")
public class BeanConfiguration {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public ClienteWebClient clienteWebClient(WebClient.Builder webClientBuilder) {
        return new ClienteWebClient(webClientBuilder);
    }

    @Bean
    public CheckoutWebClient checkoutWebClient(WebClient.Builder webClientBuilder) {
        return new CheckoutWebClient(webClientBuilder);
    }

    @Bean
    public CriarProdutoUseCase criarProdutoUseCase(SpringProdutoRepository springProdutoRepository, ProdutoEntityMapper produtoEntityMapper) {
        return new ProdutoRepositoryGateway(springProdutoRepository, produtoEntityMapper);
    }

    @Bean
    public BuscarTipoProdutoUseCase buscarTipoProdutoUseCase(SpringProdutoRepository springProdutoRepository, ProdutoEntityMapper produtoEntityMapper) {
        return new ProdutoRepositoryGateway(springProdutoRepository, produtoEntityMapper);
    }

    @Bean
    public BuscarProdutoUseCase buscarProdutoUseCase(SpringProdutoRepository springProdutoRepository, ProdutoEntityMapper produtoEntityMapper) {
        return new ProdutoRepositoryGateway(springProdutoRepository, produtoEntityMapper);
    }

    @Bean
    public EditarProdutoUseCase editarProdutoUseCase(SpringProdutoRepository springProdutoRepository, ProdutoEntityMapper produtoEntityMapper) {
        return new ProdutoRepositoryGateway(springProdutoRepository, produtoEntityMapper);
    }

    @Bean
    public RemoverProdutoUseCase removerProdutoUseCase(SpringProdutoRepository springProdutoRepository, ProdutoEntityMapper produtoEntityMapper) {
        return new ProdutoRepositoryGateway(springProdutoRepository, produtoEntityMapper);
    }

    @Bean
    public ProdutoServiceImpl produtoService(CriarProdutoUseCase criarProdutoUseCase, BuscarTipoProdutoUseCase buscarTipoProdutoUseCase,
                                             BuscarProdutoUseCase buscarProdutoUseCase, EditarProdutoUseCase editarProdutoUseCase,
                                             RemoverProdutoUseCase removerProdutoUseCase, ProdutoMapper produtoMapper) {
        return new ProdutoServiceImpl(criarProdutoUseCase, buscarTipoProdutoUseCase, buscarProdutoUseCase, editarProdutoUseCase, removerProdutoUseCase, produtoMapper);
    }

    @Bean
    public PedidoServiceImpl pedidoService(CriarPedidoUseCase criarPedidoUseCase, ListarPedidoUseCase listarPedidoUseCase,
                                           BuscarProdutoUseCase buscarProdutoUseCase, PedidoMapper pedidoMapper,
                                           ClienteWebClient clienteWebClient, CheckoutWebClient checkoutWebClient) {
        return new PedidoServiceImpl(criarPedidoUseCase, listarPedidoUseCase, buscarProdutoUseCase, pedidoMapper, clienteWebClient, checkoutWebClient);
    }

    @Bean
    public CriarPedidoUseCase criarPedidoUseCase(SpringPedidoRepository springPedidoRepository, PedidoEntityMapper pedidoEntityMapper, EntityManager entityManager) {
        return new PedidoRepositoryGateway(springPedidoRepository, pedidoEntityMapper, entityManager);
    }
}

package com.techchallenge.pagamento.configuration;

import com.techchallenge.pagamento.application.events.EventPublisher;
import com.techchallenge.pagamento.application.events.PaymentService;
import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.application.usecases.CheckoutServiceImpl;
import com.techchallenge.pagamento.application.usecases.ClienteServiceImpl;
import com.techchallenge.pagamento.application.usecases.PagamentoServiceImpl;
import com.techchallenge.pagamento.application.usecases.PedidoServiceImpl;
import com.techchallenge.pagamento.infrastructure.gateways.CheckoutRepositoryGateway;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutMapper;
import com.techchallenge.pagamento.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.cliente.ClienteMapper;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.repository.checkout.SpringCheckoutRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.cliente.SpringClienteRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.pedido.SpringPedidoRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.produto.SpringProdutoRepository;
import com.techchallenge.pagamento.infrastructure.scheduler.Scheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.techchallenge.pagamento.application",
        "com.techchallenge.pagamento.domain",
        "com.techchallenge.pagamento.infrastructure.gateways",
        "com.techchallenge.pagamento.infrastructure.mapper",
        "com.techchallenge.pagamento.infrastructure.persistence.repository",
        "com.techchallenge.pagamento.infrastructure.controllers"
})
@EntityScan(basePackages = "com.techchallenge.pagamento.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.techchallenge.pagamento.infrastructure.persistence.repository")
public class BeanConfiguration {
    @Bean
    public CheckoutUseCase checkoutUseCase(SpringClienteRepository clienteRepository, SpringProdutoRepository produtoRepository,
                                           SpringPedidoRepository pedidoRepository, SpringCheckoutRepository checkoutRepository,
                                           ClienteEntityMapper clienteEntityMapper, ProdutoEntityMapper produtoEntityMapper,
                                           PedidoEntityMapper pedidoEntityMapper, CheckoutEntityMapper checkoutEntityMapper) {
        return new CheckoutRepositoryGateway(clienteRepository, produtoRepository, pedidoRepository, checkoutRepository, clienteEntityMapper, produtoEntityMapper, pedidoEntityMapper, checkoutEntityMapper);
    }

    @Bean
    public CheckoutServiceImpl checkoutService(CheckoutUseCase checkoutUseCase, CheckoutMapper checkoutMapper, EventPublisher eventPublisher) {
        return new CheckoutServiceImpl(checkoutUseCase, checkoutMapper, eventPublisher);
    }

    @Bean
    PagamentoServiceImpl pagamentoService(CheckoutUseCase checkoutUseCase, PaymentService paymentService, CheckoutMapper checkoutMapper) {
        return new PagamentoServiceImpl(checkoutUseCase, paymentService, checkoutMapper);
    }

    @Bean
    public ClienteServiceImpl clienteService(SpringClienteRepository springClienteRepository, ClienteMapper clienteMapper, ClienteEntityMapper clienteEntityMapper) {
        return new ClienteServiceImpl(springClienteRepository, clienteMapper, clienteEntityMapper);
    }

    @Bean
    public PedidoServiceImpl pedidoService(SpringPedidoRepository springPedidoRepository, SpringProdutoRepository springProdutoRepository, SpringClienteRepository springClienteRepository, ClienteEntityMapper clienteEntityMapper, ProdutoEntityMapper produtoEntityMapper) {
        return new PedidoServiceImpl(springPedidoRepository, springProdutoRepository, springClienteRepository, clienteEntityMapper, produtoEntityMapper);
    }

    @Bean
    Scheduler scheduler(CheckoutUseCase checkoutUseCase) {
        return new Scheduler(checkoutUseCase);
    }

    @Bean
    public CommandLineRunner init(Scheduler scheduler) {
        return args -> {
            scheduler.iniciarTarefaAtualizacaoStatus();
        };
    }
}

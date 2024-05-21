package com.techchallenge.pagamento.configuration;

import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.application.usecases.CheckoutServiceImpl;
import com.techchallenge.pagamento.application.usecases.PagamentoServiceImpl;
import com.techchallenge.pagamento.infrastructure.gateways.CheckoutRepositoryGateway;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutMapper;
import com.techchallenge.pagamento.infrastructure.persistence.repository.checkout.SpringCheckoutRepository;
import com.techchallenge.pagamento.infrastructure.scheduler.Scheduler;
import jakarta.persistence.EntityManager;
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
@EnableJpaRepositories(basePackages = "com.techchallenge.pagamento.infrastructure.persistence.repository.checkout")
public class BeanConfiguration {
    @Bean
    public CheckoutUseCase checkoutUseCase(SpringCheckoutRepository springCheckoutRepository, CheckoutEntityMapper checkoutEntityMapper, EntityManager entityManager) {
        return new CheckoutRepositoryGateway(springCheckoutRepository, checkoutEntityMapper, entityManager);
    }

    @Bean
    public CheckoutServiceImpl checkoutService(CheckoutUseCase checkoutUseCase, CheckoutMapper checkoutMapper) {
        return new CheckoutServiceImpl(checkoutUseCase, checkoutMapper);
    }

    @Bean
    PagamentoServiceImpl pagamentoService(CheckoutUseCase checkoutUseCase) {
        return new PagamentoServiceImpl(checkoutUseCase);
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

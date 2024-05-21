package com.techchallenge.pagamento.infrastructure.gateways;

import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.CheckoutEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.repository.checkout.SpringCheckoutRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CheckoutRepositoryGateway implements CheckoutUseCase {
    private final SpringCheckoutRepository springCheckoutRepository;
    private final CheckoutEntityMapper checkoutEntityMapper;
    private final EntityManager entityManager;

    public CheckoutRepositoryGateway(SpringCheckoutRepository springCheckoutRepository, CheckoutEntityMapper checkoutEntityMapper, EntityManager entityManager) {
        this.springCheckoutRepository = springCheckoutRepository;
        this.checkoutEntityMapper = checkoutEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void criar(Checkout checkout) {
        CheckoutEntity checkoutEntity;
        if (Objects.isNull(checkout.getId())) {
            checkoutEntity = checkoutEntityMapper.checkoutToCheckoutEntity(checkout);
        } else {
            checkoutEntity = this.springCheckoutRepository.findById(checkout.getId())
                    .orElseThrow(() -> new RuntimeException("Checkout não encontrado"));
            checkoutEntity = checkoutEntityMapper.checkoutToCheckoutEntity(checkout);
        }
        ClienteEntity clienteEntity = checkoutEntity.getPedido().getCliente();
        if (clienteEntity == null || clienteEntity.getId() == null) {
            clienteEntity = new ClienteEntity();
            clienteEntity.setNome(checkout.getPedido().getCliente().getNome());
            clienteEntity.setCpf(checkout.getPedido().getCliente().getCpf());
            clienteEntity.setEmail(checkout.getPedido().getCliente().getEmail());
        }

        clienteEntity = entityManager.merge(clienteEntity);
        checkoutEntity.getPedido().setCliente(clienteEntity);
        List<ProdutoEntity> produtos = new ArrayList<>();
        for (ProdutoEntity produtoEntity : checkoutEntity.getPedido().getProdutos()) {
            if (produtoEntity.getId() == null || entityManager.find(ProdutoEntity.class, produtoEntity.getId()) == null) {
                produtoEntity = entityManager.merge(produtoEntity);
            }
            produtos.add(produtoEntity);
        }
        checkoutEntity.getPedido().setProdutos(produtos);

        checkoutEntity.setPedido(entityManager.merge(checkoutEntity.getPedido()));
        checkoutEntity = entityManager.merge(checkoutEntity);
        this.springCheckoutRepository.save(checkoutEntity);
    }


    @Override
    public List<Checkout> listar() {
        List<CheckoutEntity> checkoutEntities = springCheckoutRepository.findAll();
        if (!checkoutEntities.isEmpty()) {
            List<Checkout> checkouts = new ArrayList<>();
            for (CheckoutEntity checkoutEntity : checkoutEntities) {
                if (!checkoutEntity.getStatus().equals("Pronto")) {
                    checkouts.add(checkoutEntityMapper.checkoutEntityToCheckout(checkoutEntity));
                }
            }
            return checkouts;
        } else {
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Override
    public Checkout buscar(Long id) {
        Optional<CheckoutEntity> byId = springCheckoutRepository.findById(id);
        if (byId.isPresent()) {
            return checkoutEntityMapper.checkoutEntityToCheckout(byId.get());
        } else {
            throw new RuntimeException("Id não encontrado");
        }
    }

    @Override
    public void atualizarPagamento(Checkout checkout) {
        CheckoutEntity checkoutEntityById = springCheckoutRepository.findById(checkout.getId()).get();
        if (Objects.nonNull(checkoutEntityById)) {
            CheckoutEntity checkoutEntity = checkoutEntityMapper.updateCheckoutEntityPagamento(checkout, checkoutEntityById);
            this.springCheckoutRepository.save(checkoutEntity);
        } else {
            throw new RuntimeException("Checkout não existe");
        }
    }

    @Override
    public void atualizarStatus(Checkout checkout) {
        CheckoutEntity checkoutEntityById = springCheckoutRepository.findById(checkout.getId()).get();
        if (Objects.nonNull(checkoutEntityById)) {
            CheckoutEntity checkoutEntity = checkoutEntityMapper.updateCheckoutEntityStatus(checkout, checkoutEntityById);
            this.springCheckoutRepository.save(checkoutEntity);
        } else {
            throw new RuntimeException("Checkout não existe");
        }
    }

    @Override
    public List<Checkout> buscarPorStatusPagamento(String status) {
        List<CheckoutEntity> checkoutEntities = springCheckoutRepository.findByStatusPagamento(status);
        return checkoutEntityMapper.checkoutEntitiesToCheckouts(checkoutEntities);
    }
}

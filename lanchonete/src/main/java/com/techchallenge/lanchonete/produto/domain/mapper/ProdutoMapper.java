package com.techchallenge.lanchonete.produto.domain.mapper;

import com.techchallenge.lanchonete.produto.domain.Dto.ProdutoDto;
import com.techchallenge.lanchonete.produto.domain.entity.Produto;
import org.mapstruct.Mapper;

@Mapper
public interface ProdutoMapper {
    Produto produtoDTOToProduto(ProdutoDto produtoDto);
}

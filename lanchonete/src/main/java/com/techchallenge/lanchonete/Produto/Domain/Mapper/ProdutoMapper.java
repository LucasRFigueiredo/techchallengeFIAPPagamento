package com.techchallenge.lanchonete.Produto.Domain.Mapper;

import com.techchallenge.lanchonete.Produto.Domain.Dto.ProdutoDto;
import com.techchallenge.lanchonete.Produto.Domain.Entity.Produto;
import org.mapstruct.Mapper;

@Mapper
public interface ProdutoMapper {
    Produto produtoDTOToProduto(ProdutoDto produtoDto);
}

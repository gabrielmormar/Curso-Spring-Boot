package com.gabriel.modelagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.modelagem.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
}


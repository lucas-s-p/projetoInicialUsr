package com.devlucas.usrfacil.repository;

import com.devlucas.usrfacil.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long> {
}

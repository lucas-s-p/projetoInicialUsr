package com.devlucas.usrfacil.repository;

import com.devlucas.usrfacil.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

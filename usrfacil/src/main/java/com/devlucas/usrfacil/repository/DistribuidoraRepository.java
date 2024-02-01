package com.devlucas.usrfacil.repository;

import com.devlucas.usrfacil.model.Distribuidora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribuidoraRepository extends JpaRepository<Distribuidora, Long> {
}

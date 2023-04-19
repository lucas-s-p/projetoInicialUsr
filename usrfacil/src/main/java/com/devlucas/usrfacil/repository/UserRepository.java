package com.devlucas.usrfacil.repository;

import com.devlucas.usrfacil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.munushop.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munushop.server.entities.Usuario;



public interface UserRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUsername(String username);
}
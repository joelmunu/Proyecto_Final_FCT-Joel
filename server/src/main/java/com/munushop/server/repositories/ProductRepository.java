package com.munushop.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.munushop.server.entities.Producto;


@Repository
public interface ProductRepository extends JpaRepository<Producto, Long>{
    @Query("SELECT p FROM Producto p")
    Producto getProducts();

    Producto findByNombre(String nombre);
}

package com.example.examenjulianjpa.repository;

import com.example.examenjulianjpa.domain.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}

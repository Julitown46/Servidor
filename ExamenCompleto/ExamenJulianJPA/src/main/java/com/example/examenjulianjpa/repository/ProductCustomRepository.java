package com.example.examenjulianjpa.repository;

import com.example.examenjulianjpa.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCustomRepository {
    public List<Product> findByColVal(String[] colVal);
    public List<Product> ordenar(String[] colSentido);
}

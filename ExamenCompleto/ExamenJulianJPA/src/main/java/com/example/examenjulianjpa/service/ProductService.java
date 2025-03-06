package com.example.examenjulianjpa.service;

import com.example.examenjulianjpa.domain.Product;
import com.example.examenjulianjpa.repository.ProductCustomRepository;
import com.example.examenjulianjpa.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
            private ProductCustomRepository productCustomRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).get();
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public void delete(Long id) {
        this.productRepository.findById(id).map(p -> {this.productRepository.delete(p);
                                                                return p;});
    }

    public Product update(Long id, Product product) {
        if (this.productRepository.existsById(id)) {
            return this.productRepository.save(product);
        } else {
            log.info("No se puede encontrar producto");
            return null;
        }
    }

    public List<Product> buscar(String[] colVal){
        return productCustomRepository.findByColVal(colVal);
    }

    public Map<String, Object> paginado(int pagina, int tamano) {
        Pageable paginado = PageRequest.of(pagina, tamano, Sort.by("idProduct").ascending());

        Page<Product> pageAll = this.productRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("productos", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public List<Product> ordenar(String[] colSentido) {
        return productCustomRepository.ordenar(colSentido);
    }
}

package com.example.examenjulianjpa.service;

import com.example.examenjulianjpa.domain.Carrito;
import com.example.examenjulianjpa.domain.Product;
import com.example.examenjulianjpa.domain.User;
import com.example.examenjulianjpa.repository.CarritoRepository;
import com.example.examenjulianjpa.repository.ProductRepository;
import com.example.examenjulianjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void addToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

//        Carrito carrito = new Carrito(1 , userId, productId, quantity);
//
//        carritoRepository.save(cartItem);
    }
}

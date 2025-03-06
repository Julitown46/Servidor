package com.example.examenjulianjpa.service;

import com.example.examenjulianjpa.domain.Product;
import com.example.examenjulianjpa.domain.User;
import com.example.examenjulianjpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        this.userRepository.findById(id).map(u -> {this.userRepository.delete(u);
            return u;});
    }

    public User update(Long id, User user) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.save(user);
        } else {
            log.info("No se puede encontrar usuario");
            return null;
        }
    }
}

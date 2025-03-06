package com.example.examenjulianjpa.service;

import com.example.examenjulianjpa.domain.Category;
import com.example.examenjulianjpa.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        this.categoryRepository.findById(id).map(c -> {this.categoryRepository.delete(c);
            return c;});
    }

    public Category update(Long id, Category category) {
        if (categoryRepository.findById(id).isPresent()) {
            return categoryRepository.save(category);
        } else {
            log.info("No se puede encontrar categoria");
            return null;
        }
    }
}

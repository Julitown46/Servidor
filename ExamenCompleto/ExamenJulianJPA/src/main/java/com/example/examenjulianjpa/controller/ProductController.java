package com.example.examenjulianjpa.controller;

import com.example.examenjulianjpa.domain.Product;
import com.example.examenjulianjpa.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!pagina", "!tamano", "!ordenar"})
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(value = {"","/"})
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.delete(id);
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamano", "!ordenar"})
    public ResponseEntity<List<Product>> buscarProducts(@RequestParam(value="buscar") String[] colVal) {

        List<Product> product = productService.buscar(colVal);

        return ResponseEntity.ok(product);
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar"})
    public ResponseEntity<Map<String, Object>> productsPaginado(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                                        @RequestParam(value = "tamano", defaultValue = "3") int tamanio) {

        Map<String, Object> responseAll = this.productService.paginado(pagina, tamanio);

        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamano", "!buscar"})
    public ResponseEntity<List<Product>> ordenarProducts(@RequestParam(value="ordenar") String[] colSentido) {

        List<Product> product = productService.ordenar(colSentido);

        return ResponseEntity.ok(product);
    }
}

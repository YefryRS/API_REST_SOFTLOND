package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.Product;
import com.api.rest.softlond.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {

        productService.saveOrUpdate(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> save(@RequestBody Product product, @PathVariable Long id) {

        product.setId(id);
        productService.saveOrUpdate(product);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

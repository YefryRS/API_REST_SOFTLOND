package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.CategoriyProduct;
import com.api.rest.softlond.service.ICategoryProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryProductController {

    private final ICategoryProductService categoryProductService;

    public CategoryProductController(ICategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriyProduct>> findAll() {
        return ResponseEntity.ok(categoryProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoriyProduct>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryProductService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriyProduct> save(@RequestBody CategoriyProduct categoriyProduct) {

        categoryProductService.saveOrUpdate(categoriyProduct);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriyProduct> save(@RequestBody CategoriyProduct categoriyProduct, @PathVariable Long id) {

        categoriyProduct.setId(id);
        categoryProductService.saveOrUpdate(categoriyProduct);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryProductService.delete(id);

        return ResponseEntity.noContent().build();
    }

}

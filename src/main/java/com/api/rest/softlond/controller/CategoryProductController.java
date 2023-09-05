package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.CategoriyProduct;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.service.ICategoryProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryProductController {

    private final ICategoryProductService categoryProductService;
    Logger logger = LoggerFactory.getLogger(CategoriyProduct.class);

    public CategoryProductController(ICategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriyProduct>> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        List<CategoriyProduct> categoriys = categoryProductService.findAll();
        if (categoriys.isEmpty()) {
            logger.info("No hay ninguna categoria en la base de datos");
            throw new LocalNotFoundException("No existe ninguna categoria");
        } else if (!categoriys.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoriys);
        }
        logger.info("Ha ocurrido un error interno del servidor");
        throw new LocalInternalServerErrorException("Ha ocurrido un error interno");

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoriyProduct>> findById(@PathVariable Long id) throws LocalNotFoundException {
        Optional<CategoriyProduct> category = categoryProductService.findById(id);

        if(category.isEmpty()) {
            logger.warn("El id enviado no existe, por tanto la categoria no fue encontrado");
            throw new LocalNotFoundException("La categoria no fue encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @PostMapping
    public ResponseEntity<CategoriyProduct> save(@Valid @RequestBody CategoriyProduct categoriyProduct) {
        categoryProductService.saveOrUpdate(categoriyProduct);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriyProduct> save(@RequestBody CategoriyProduct categoriyProduct, @PathVariable Long id) throws LocalNotFoundException {
        Optional<CategoriyProduct> categoryId = categoryProductService.findById(id);
        if (categoryId.isEmpty()) {
            logger.info("Id incorrecto, este no existe");
            throw new LocalNotFoundException("El id no existe, por lo tanto la categoria no fue encontrada");
        }

        categoriyProduct.setId(id);
        categoryProductService.saveOrUpdate(categoriyProduct);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws LocalNotFoundException {
        Optional<CategoriyProduct> categoryId = categoryProductService.findById(id);
        if (categoryId.isEmpty()) {
            logger.info("Id incorrecto, este no existe");
            throw new LocalNotFoundException("El id no existe, por lo tanto la categoria no es posible eliminarla");
        }
        categoryProductService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.Product;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.service.IProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        List<Product> products = productService.findAll();

        if(products.isEmpty()) {
            logger.info("Aun no hay productos en la base de datos");
            throw new LocalNotFoundException("No hay ningun producto en la base de datos");
        }
        else if (!products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        logger.error("Error interno del servidor");
        throw new LocalInternalServerErrorException("Error interno");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) throws LocalNotFoundException {
        Optional<Product> product = productService.findById(id);

        if(product.isEmpty()) {
            logger.warn("El id enviado no existe, por tanto el producto no fue encontrado");
            throw new LocalNotFoundException("El producto no fue encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {

        productService.saveOrUpdate(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> save(@RequestBody Product product, @PathVariable Long id) throws LocalNotFoundException {
        Optional<Product> productId = productService.findById(id);

        if(productId.isEmpty()) {
            logger.error("El id enviado no existe, por tanto el producto no es posible realizar la actualizacion");
            throw new LocalNotFoundException("El producto no fue encontrado");
        }

        product.setId(id);
        productService.saveOrUpdate(product);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws LocalNotFoundException {
        Optional<Product> productId = productService.findById(id);

        if(productId.isEmpty()) {
            logger.error("El id enviado no existe, por tanto no es posible eliminar el producto");
            throw new LocalNotFoundException("El producto no fue encontrado");
        }
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

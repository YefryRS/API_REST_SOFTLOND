package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Product;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) throws LocalNotFoundException {
        return productRepository.findById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) throws LocalNotFoundException {
        productRepository.deleteById(id);
    }

}

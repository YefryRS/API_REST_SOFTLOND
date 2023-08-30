package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public List<Product> findAll();
    public Optional<Product> findById(Long id);
    public Product saveOrUpdate(Product product);
    public void delete(Long id);

}

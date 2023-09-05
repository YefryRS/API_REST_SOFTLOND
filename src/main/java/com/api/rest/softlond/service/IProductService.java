package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Product;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public List<Product> findAll() throws LocalNotFoundException, LocalInternalServerErrorException;
    public Optional<Product> findById(Long id) throws LocalNotFoundException;
    public Product saveOrUpdate(Product product);
    public void delete(Long id) throws LocalNotFoundException;

}

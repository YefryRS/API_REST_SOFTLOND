package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.CategoriyProduct;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ICategoryProductService {

    public List<CategoriyProduct> findAll() throws LocalNotFoundException, LocalInternalServerErrorException;
    public Optional<CategoriyProduct> findById(Long id) throws LocalNotFoundException;
    public CategoriyProduct saveOrUpdate(CategoriyProduct categoriyProduct);
    public void delete(Long id) throws LocalNotFoundException;

}

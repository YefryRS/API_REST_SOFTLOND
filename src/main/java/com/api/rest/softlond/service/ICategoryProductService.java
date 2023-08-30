package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.CategoriyProduct;

import java.util.List;
import java.util.Optional;

public interface ICategoryProductService {

    public List<CategoriyProduct> findAll();
    public Optional<CategoriyProduct> findById(Long id);
    public CategoriyProduct saveOrUpdate(CategoriyProduct categoriyProduct);
    public void delete(Long id);

}

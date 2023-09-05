package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.CategoriyProduct;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.repository.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryProductService implements ICategoryProductService{

    private final CategoryProductRepository categoryproductRepository;

    @Autowired
    public CategoryProductService(CategoryProductRepository categoryproductRepository) {
        this.categoryproductRepository = categoryproductRepository;
    }

    @Override
    public List<CategoriyProduct> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        return categoryproductRepository.findAll();
    }

    @Override
    public Optional<CategoriyProduct> findById(Long id) throws LocalNotFoundException {
        return categoryproductRepository.findById(id);
    }

    @Override
    public CategoriyProduct saveOrUpdate(CategoriyProduct categoriyProduct) {
        return categoryproductRepository.save(categoriyProduct);
    }

    @Override
    public void delete(Long id) throws LocalNotFoundException {
        categoryproductRepository.deleteById(id);
    }

}

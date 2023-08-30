package com.api.rest.softlond.repository;

import com.api.rest.softlond.entity.CategoriyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoriyProduct,Long> {
}

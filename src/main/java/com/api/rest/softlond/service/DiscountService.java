package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Discount;
import com.api.rest.softlond.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Optional<Discount> findById(Long id) {
        return discountRepository.findById(id);
    }

    public Discount saveOrUpdate(Discount discount) {
        return discountRepository.save(discount);
    }

}

package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.Discount;
import com.api.rest.softlond.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Discount>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(discountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Discount> saveOrUpdate(@RequestBody Discount discount) {
        return ResponseEntity.ok().body(discountService.saveOrUpdate(discount));
    }

}

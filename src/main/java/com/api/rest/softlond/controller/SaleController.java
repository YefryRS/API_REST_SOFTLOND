package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.Sale;
import com.api.rest.softlond.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    private final ISaleService saleService;

    @Autowired
    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> findAll() {
        return ResponseEntity.ok(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sale>> findAll(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.findById(id));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Sale>> findByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(saleService.findByDate(date));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Sale>> findByClient(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.findByClient(id));
    }

    @GetMapping("/date/client/{id}/{datefirst}/{dateend}")
    public ResponseEntity<List<Sale>> findByClientAndDate(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate datefirst,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateend) {

        return ResponseEntity.ok(saleService.findByClientAndDate(id,datefirst,dateend));
    }

    @PostMapping
    public ResponseEntity<Sale> save(@RequestBody Sale sale) {

        saleService.saveOrUpdate(sale);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Sale> save(@RequestBody Sale sale, @PathVariable Long id) {

        sale.setId(id);
        saleService.saveOrUpdate(sale);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        saleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

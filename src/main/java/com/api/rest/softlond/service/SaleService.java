package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Sale;
import com.api.rest.softlond.repository.SaleRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements ISaleService{

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    public Sale saveOrUpdate(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void delete(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public List<Sale> findByDate(LocalDate date) {
        return saleRepository.findByDate(date);
    }

    @Override
    public List<Sale> findByClient(Long id) {
        return saleRepository.findByClient(id);
    }

    @Override
    public List<Sale> findByClientAndDate(Long id, LocalDate dateFirst, LocalDate dateEnd) {
        return saleRepository.findByClientAndDate(id,dateFirst,dateEnd);
    }
}

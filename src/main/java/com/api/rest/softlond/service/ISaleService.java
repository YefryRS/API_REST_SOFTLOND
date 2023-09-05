package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Sale;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISaleService {

    public List<Sale> findAll();
    public List<Sale> findByDate(LocalDate date);
    public List<Sale> findByClient(Long id);
    public List<Sale> findByClientAndDate(Long id,LocalDate dateFirst,LocalDate dateEnd);
    public Optional<Sale> findById(Long id);
    public Sale saveOrUpdate(Sale sale);
    public void delete(Long id);

}

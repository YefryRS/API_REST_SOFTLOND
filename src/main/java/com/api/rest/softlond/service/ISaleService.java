package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Sale;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISaleService {

    public List<Sale> findAll() throws LocalNotFoundException, LocalInternalServerErrorException;
    public List<Sale> findByDate(LocalDate date) throws LocalNotFoundException;
    public List<Sale> findByClient(Long id) throws LocalNotFoundException;
    public List<Sale> findByClientAndDate(Long id,LocalDate dateFirst,LocalDate dateEnd) throws LocalNotFoundException;
    public Optional<Sale> findById(Long id) throws LocalNotFoundException;
    public Sale save(Sale sale);
    public Sale update(Sale sale);
    public void delete(Long id) throws LocalNotFoundException;

}

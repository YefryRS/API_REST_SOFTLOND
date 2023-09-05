package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Client;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    public List<Client> findAll() throws LocalNotFoundException, LocalInternalServerErrorException;
    public Optional<Client> findById(Long id) throws LocalNotFoundException;
    public Client saveOrUpdate(Client client);
    public void delete(Long id) throws LocalNotFoundException;

}

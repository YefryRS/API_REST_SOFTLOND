package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    public List<Client> findAll();
    public Optional<Client> findById(Long id);
    public Client saveOrUpdate(Client client);
    public void delete(Long id);

}

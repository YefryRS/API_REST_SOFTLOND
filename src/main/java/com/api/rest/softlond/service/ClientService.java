package com.api.rest.softlond.service;

import com.api.rest.softlond.entity.Client;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) throws LocalNotFoundException {
        return clientRepository.findById(id);
    }

    @Override
    public Client saveOrUpdate(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) throws LocalNotFoundException{
        clientRepository.deleteById(id);
    }

}

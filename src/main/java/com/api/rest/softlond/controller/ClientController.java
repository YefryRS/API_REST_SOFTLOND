package com.api.rest.softlond.controller;

import com.api.rest.softlond.entity.Client;
import com.api.rest.softlond.error.LocalInternalServerErrorException;
import com.api.rest.softlond.error.LocalNotFoundException;
import com.api.rest.softlond.service.IClientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final IClientService clientService;
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() throws LocalNotFoundException, LocalInternalServerErrorException {
        List<Client> clients = clientService.findAll();

        if(clients.isEmpty()) {
            logger.info("Aun no hay clientes en la base de datos");
            throw new LocalNotFoundException("No hay ningun cliente en la base de datos");
        }
        else if (!clients.isEmpty()) {
            return ResponseEntity.ok().body(clients);
        }
        logger.error("Error interno del servidor");
        throw new LocalInternalServerErrorException("Error interno");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> findById(@PathVariable Long id) throws LocalNotFoundException {
        Optional<Client> client = clientService.findById(id);

        if(client.isEmpty()) {
            logger.warn("El id enviado no existe, por tanto el cliente no fue encontrado");
            throw new LocalNotFoundException("El cliente no fue encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PostMapping
    public ResponseEntity<Client> save(@Valid @RequestBody Client client) {
        clientService.saveOrUpdate(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@RequestBody Client client,@PathVariable Long id) throws LocalNotFoundException {
        Optional<Client> clientId = clientService.findById(id);

        if(clientId.isEmpty()) {
            logger.error("El id enviado no existe, por tanto el cliente no es posible realizar la actualizacion");
            throw new LocalNotFoundException("El cliente no fue encontrado");
        }
        client.setId(id);
        clientService.saveOrUpdate(client);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws LocalNotFoundException {
        Optional<Client> client = clientService.findById(id);
        if (client.isEmpty()) {
            logger.error("El cliente no existe");
            throw new LocalNotFoundException("El cliente a eliminar no existe");
        }

        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package com.ms.client.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.client.dtos.ClientDto;
import com.ms.client.models.Client;
import com.ms.client.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientDto clientDto) {
        return clientService.saveClient(clientDto);
    }

    @GetMapping
    @JsonIgnore //para não ficar no loop infinito de repetir address e userID.
    public ResponseEntity<List<Client>> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClientbyID(@PathVariable(value = "id")UUID id) {
        return clientService.getOneClientByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id")UUID id,
                                               @RequestBody @Valid ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "id")UUID id) {
        return clientService.deleteCLient(id);
    }
}

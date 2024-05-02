package com.ms.client.services;

import com.ms.client.dtos.ClientDto;
import com.ms.client.models.Client;
import com.ms.client.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final Client client;

    public ResponseEntity<Client> saveClient(ClientDto clientDto) {
        BeanUtils.copyProperties(clientDto, client);
        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Client>> getAllClients() {
        try {
            return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> getOneClientByID(UUID id) {
        try{
            var dbClient = clientRepository.findById(id);
            if(dbClient.isEmpty()){return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);}
            return new ResponseEntity<>(dbClient, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Object(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> updateClient(UUID id, ClientDto clientDto) {
        try{
            var dbClient = clientRepository.findById(id);
            var clientModel = dbClient.get();
            BeanUtils.copyProperties(clientDto, clientModel);
            return new ResponseEntity<>(clientRepository.save(clientModel), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteCLient(UUID id) {
        try {
            var client = clientRepository.findById(id);
            if (client.isPresent()) {
                clientRepository.deleteById(id);
                return new ResponseEntity<>("Client deleted successfully.", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Unable to delete Client. Client not found!", HttpStatus.NOT_FOUND);
    }
}

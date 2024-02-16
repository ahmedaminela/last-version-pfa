package com.car.rent_car.services;

import com.car.rent_car.models.Client;
import com.car.rent_car.requests.ClientRequest;
import com.car.rent_car.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(ClientRequest clientRequest) {
        Client client = new Client();
        BeanUtils.copyProperties(clientRequest, client);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client updateClient(Long id, ClientRequest clientRequest) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            BeanUtils.copyProperties(clientRequest, existingClient);
            return clientRepository.save(existingClient);
        }
        return null; // or throw an exception indicating client not found
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}

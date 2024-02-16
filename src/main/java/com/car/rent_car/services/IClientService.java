package com.car.rent_car.services;

import com.car.rent_car.models.Client;
import com.car.rent_car.requests.ClientRequest;

import java.util.List;

public interface IClientService {

    Client createClient(ClientRequest clientRequest);

    List<Client> getAllClients();

    Client getClientById(Long id);

    Client updateClient(Long id, ClientRequest clientRequest);

    void deleteClient(Long id);
}

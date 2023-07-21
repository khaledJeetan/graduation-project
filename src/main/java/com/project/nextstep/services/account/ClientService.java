package com.project.nextstep.services.account;

import com.project.nextstep.entity.accounts.Client;
import com.project.nextstep.repositories.account.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getSuppliers() {
        return clientRepository.findAll();
    }

    public Client getClient(Long supplierId) {
        return clientRepository
                .findById(supplierId)
                .orElseThrow(()->
                        new IllegalStateException("Supplier Not Found")
                );
    }

    public void addSupplier(Client client) {
        clientRepository.save(client);
    }

}

package com.project.nextstep.services.account;


import com.project.nextstep.entity.accounts.Supplier;
import com.project.nextstep.repositories.account.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(String supplierEmail) {
        return supplierRepository
                .findByEmail(supplierEmail)
                .orElseThrow(()->
                        new IllegalStateException("Supplier Not Found")
                );
    }

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

}

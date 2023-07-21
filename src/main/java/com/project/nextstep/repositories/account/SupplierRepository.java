package com.project.nextstep.repositories.account;

import com.project.nextstep.entity.accounts.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    @Query("SELECT s FROM supplier s WHERE s.email = :email")
    public Optional<Supplier> findByEmail(String email);

}

package com.project.nextstep.repositories.account;

import com.project.nextstep.entity.accounts.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}

package com.project.nextstep.repositories.account;

import com.project.nextstep.entity.accounts.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
}

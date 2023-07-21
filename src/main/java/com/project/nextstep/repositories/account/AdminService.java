package com.project.nextstep.repositories.account;

import com.project.nextstep.entity.accounts.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminService extends JpaRepository<Admin,Long> {
}

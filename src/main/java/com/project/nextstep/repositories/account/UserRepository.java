package com.project.nextstep.repositories.account;
import com.project.nextstep.entity.accounts.User;
import com.project.nextstep.entity.accounts.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    boolean deleteByEmail(String email);

    List<Vendor> findByIdIn(List<Long> idList);
}

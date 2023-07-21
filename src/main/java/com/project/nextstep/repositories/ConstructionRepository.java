package com.project.nextstep.repositories;

import com.project.nextstep.entity.Construction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionRepository extends JpaRepository<Construction,Long> {


    Page<Construction> findAllByClientId(Long clientId, Pageable pageable);

}

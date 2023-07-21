package com.project.nextstep.repositories;

import com.project.nextstep.entity.construction.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {

//    @Query("select s from Service s where s.name like :serviceType and s.price between :minPrice and :maxPrice")
    List<Service> findServicesByNameAndPriceBetween(String serviceType, double minPrice, double maxPrice);

}

package com.project.nextstep.repositories;

import com.project.nextstep.entity.construction.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllBySupplier_Id(Long supplier_id);

}

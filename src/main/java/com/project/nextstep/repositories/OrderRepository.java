package com.project.nextstep.repositories;

import com.project.nextstep.entity.construction.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByProduct_Supplier_Id(Long supplierId);
    List<Order> findAllByTask_Construction_Client_Id(Long clientId);

}

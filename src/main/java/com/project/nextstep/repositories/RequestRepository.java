package com.project.nextstep.repositories;

import com.project.nextstep.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    List<Request> findAllByVendor_Id(Long vendorId);
    List<Request> findAllByTask_Id(Long taskId);

}

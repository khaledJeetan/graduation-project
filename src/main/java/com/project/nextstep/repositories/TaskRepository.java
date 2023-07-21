package com.project.nextstep.repositories;

import com.project.nextstep.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findAllByVendor_Id(Long vendorId);

    @Query("SELECT (SUM (t.progress) / COUNT (t))  FROM Task t WHERE t.construction.id = :constructionId")
    double calculateConstructionProgress(Long constructionId);

}

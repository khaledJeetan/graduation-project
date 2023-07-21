package com.project.nextstep.repositories;

import com.project.nextstep.entity.Chat;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

    List<Chat> findAllByUser1_IdOrUser2_Id(Long userId,Long user2Id);

    @Transactional
    @Modifying
    @Query("update Task t set t.vendor.id = null where t.vendor.id = :userId")
    void updateTaskVendorIdToNull(Long userId);

}

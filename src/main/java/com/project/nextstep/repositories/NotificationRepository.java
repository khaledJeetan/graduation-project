package com.project.nextstep.repositories;

import com.project.nextstep.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findAllByUser_Id(Long userId);
}

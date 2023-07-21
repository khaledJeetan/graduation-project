package com.project.nextstep.services;

import com.project.nextstep.entity.Notification;
import com.project.nextstep.entity.accounts.User;
import com.project.nextstep.repositories.NotificationRepository;
import com.project.nextstep.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }


    public List<Notification> getAllUserNotification(Long userId) {
       return notificationRepository.findAllByUser_Id(userId);
    }

    @Transactional
    public List<Notification> setAllUserNotificationRead(Long userId) {
        getAllUserNotification(userId).forEach(notification -> notification.setRead(true));
        return getAllUserNotification(userId);
    }

    public Notification createUserNotification(Long userId, Notification notification) {
        User user = userService.getUser(userId);
        notification.setUser(user);
        return notificationRepository.save(notification);
    }
}

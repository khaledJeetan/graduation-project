package com.project.nextstep.controllers;

import com.project.nextstep.entity.Notification;
import com.project.nextstep.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


//    @MessageMapping("${pr}")
//    @SendTo()
//    public Notification getAllUserNotification(Notification notification){
//        return notification;
//    }

    @GetMapping
    public List<Notification> getAllUserNotification(
            @PathVariable("userId") Long userId){
        return notificationService.getAllUserNotification(userId);
    }

    @PutMapping
    public List<Notification> setAllUserNotificationRead(
            @PathVariable("userId") Long userId){
        return notificationService.setAllUserNotificationRead(userId);
    }

    @PostMapping
    public Notification createUserNotification(
            @PathVariable("userId") Long userId,
            @RequestBody Notification notification
            ){
        return notificationService.createUserNotification(userId,notification);
    }







}

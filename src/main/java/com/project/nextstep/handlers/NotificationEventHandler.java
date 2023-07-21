package com.project.nextstep.handlers;


import com.project.nextstep.config.NotificationProperties;
import com.project.nextstep.entity.Notification;
import jakarta.persistence.PostPersist;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class NotificationEventHandler {

    private SimpMessagingTemplate simpMessagingTemplate;
    private NotificationProperties notificationProperties;

    @Autowired
    public NotificationEventHandler(SimpMessagingTemplate simpMessagingTemplate, NotificationProperties notificationProperties) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationProperties = notificationProperties;
    }

    @PostPersist
    public void handleNotificationAfterSave(Notification notification){
        System.out.println("New Message saved ----------------\nSending WebSocket Notification");
        String destination = this.notificationProperties
                .getBroker()
                .concat("/")
                .concat(String.valueOf(notification.getUser().getId()));

        this.simpMessagingTemplate.convertAndSend(destination,"notify");
    }
}

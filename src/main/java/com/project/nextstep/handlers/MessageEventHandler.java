package com.project.nextstep.handlers;

import com.project.nextstep.config.NotificationProperties;
import com.project.nextstep.entity.Message;
import jakarta.persistence.PostPersist;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class MessageEventHandler {

    private  SimpMessagingTemplate simpMessagingTemplate;
    private  NotificationProperties notificationProperties;

    @Autowired
    public MessageEventHandler(SimpMessagingTemplate simpMessagingTemplate, NotificationProperties notificationProperties) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationProperties = notificationProperties;
    }

    @PostPersist
    public void handleMessageAfterSave(Message message){

        System.out.println("New Message saved *****************\n");

        String destinationUser1 = this.notificationProperties
                .getBroker()
                .concat("/")
                .concat(String.valueOf(message.getChat().getUser1().getId()));

        String destinationUser2 = this.notificationProperties
                .getBroker()
                .concat("/")
                .concat(String.valueOf(message.getChat().getUser2().getId()));
        System.out.println("user 1 :- "+destinationUser1+"\t\n*********");
        System.out.println("user 1 :- "+destinationUser2+"\t\n*********");
        this.simpMessagingTemplate.convertAndSend(destinationUser1,"message");
        this.simpMessagingTemplate.convertAndSend(destinationUser2,"message");
    }

}

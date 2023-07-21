package com.project.nextstep.services;

import com.project.nextstep.entity.Chat;
import com.project.nextstep.entity.Message;
import com.project.nextstep.entity.Notification;
import com.project.nextstep.entity.enums.NotificationType;
import com.project.nextstep.handlers.MessageEventHandler;
import com.project.nextstep.repositories.ChatRepository;
import com.project.nextstep.services.account.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;
    private final NotificationService notificationService;
    private final MessageEventHandler messageEventHandler;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserService userService, NotificationService notificationService, MessageEventHandler messageEventHandler) {
        this.chatRepository = chatRepository;
        this.userService = userService;
        this.notificationService = notificationService;
        this.messageEventHandler = messageEventHandler;
    }


    public List<Chat> getAllUserChats(Long userId) {
        return chatRepository.findAllByUser1_IdOrUser2_Id(userId,userId);
    }

    public Chat createUserChat(Long userId,Long user2Id) {
        Chat chat =  Chat
                .builder()
                .user1(userService.getUser(userId))
                .user2(userService.getUser(user2Id))
                .build();
        return chatRepository.save(chat);
    }

    @Transactional
    public Chat creatUserChatMessage(Long userId, Long chatId, Message message) {
      Chat chat = chatRepository.findById(chatId).orElseThrow(
                () -> new ObjectNotFoundException(Chat.class, String.valueOf(chatId))
        );
      message.setChat(chat);
      chat.getMessages().add(message);
      messageEventHandler.handleMessageAfterSave(message);
        Notification n1 = Notification
                .builder()
                .message("You have a new message from "+ chat.getUser1().getFirstName())
                .type(NotificationType.NEW_MESSAGE)
                .url(chat.getUser1().getUrl())
                .build();
        notificationService.createUserNotification(chat.getUser2().getId(),n1);
        int [] nums;
        List<Integer> arr;
        return chat;
    }

}

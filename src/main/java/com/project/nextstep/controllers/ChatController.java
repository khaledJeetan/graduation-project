package com.project.nextstep.controllers;

import com.project.nextstep.entity.Chat;
import com.project.nextstep.entity.Message;
import com.project.nextstep.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}")
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chats")
    public List<Chat> getAllUserChats(
            @PathVariable("userId") Long userId
    ){
        return chatService.getAllUserChats(userId);
    }

    @PostMapping("/chats/{user2Id}")
    public ResponseEntity<Chat> createUserChat(
            @PathVariable("userId") Long userId,
            @PathVariable("user2Id") Long user2Id
    ){
        Chat createdChat = chatService.createUserChat(userId,user2Id);
        return ResponseEntity.ok(createdChat);
    }

    @PostMapping("/chats/{chatId}/messages")
    public ResponseEntity<Chat> creatUserChatMessage(
            @PathVariable("userId") Long userId,
            @PathVariable("chatId") Long chatId,
            @RequestBody Message message
    ){
        Chat updatedChat = chatService.creatUserChatMessage(userId,chatId,message);
        return ResponseEntity.ok(updatedChat);
    }


}

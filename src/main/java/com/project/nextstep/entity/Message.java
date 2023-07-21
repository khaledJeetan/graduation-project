package com.project.nextstep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"chat"})
public class Message extends EntityDateMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    @SequenceGenerator(name = "message_generator", sequenceName = "message_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties({
            "dateCreated",
            "lastModified",
            "constructionList",
            "password",
            "tasks",
            "ratingReviews",
            "products",
            "service",
            "chats"
    })
    private User sender;
    private String message;
    private boolean isRead = false;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "chat_id")
    private Chat chat;

}

package com.project.nextstep.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.User;
import com.project.nextstep.entity.enums.NotificationType;
import com.project.nextstep.handlers.NotificationEventHandler;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(NotificationEventHandler.class)
public class Notification extends EntityDateMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_generator")
    @SequenceGenerator(name = "notification_generator", sequenceName = "notification_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;

    private boolean isRead = false;

    private String url;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    @With
    @JsonIgnoreProperties({
            "dateCreated",
            "lastModified",
            "constructionList",
            "password",
            "tasks",
            "ratingReviews",
            "products",
            "service",
            "chats",
            "notifications"
    })
    private User user;

}

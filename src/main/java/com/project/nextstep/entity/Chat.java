package com.project.nextstep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_generator")
    @SequenceGenerator(name = "chat_generator", sequenceName = "chat_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
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
    private User user1;


    @ManyToOne
    @JoinColumn(name = "user2_id")
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
    private User user2;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    @JsonIgnoreProperties({"chat"})
    private List<Message> messages;


}

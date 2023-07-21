package com.project.nextstep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.accounts.Client;
import com.project.nextstep.entity.accounts.User;
import com.project.nextstep.entity.construction.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class  RatingReview extends EntityDateMetadata{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_review_generator")
    @SequenceGenerator(name = "rating_review_generator", sequenceName = "rating_review_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private double rating;

    private String review;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reviewer_id")
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
    private Client reviewer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reviewed_id")
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
    private User reviewedUser;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("ratingReviews")
    private Product product;

}

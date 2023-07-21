package com.project.nextstep.entity.construction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.RatingReview;
import com.project.nextstep.entity.accounts.Supplier;
import com.project.nextstep.entity.enums.HouseFinishingTask;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Product extends Offer<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HouseFinishingTask category;

    private String url;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            mappedBy = "product"
    )
    @JsonIgnoreProperties("product")
    private List<RatingReview> ratingReviews;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    @JsonIgnoreProperties("products")
    private Supplier supplier;

}

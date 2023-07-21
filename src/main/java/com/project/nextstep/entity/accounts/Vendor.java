package com.project.nextstep.entity.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.nextstep.entity.RatingReview;
import com.project.nextstep.entity.Task;
import com.project.nextstep.entity.construction.Service;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "vendor")
@DiscriminatorValue("VENDOR")
public class Vendor extends User {

    @OneToOne(
            mappedBy = "vendor",
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties("vendor")
    private Service service;

    @OneToMany(
            mappedBy = "reviewedUser",
            cascade = CascadeType.REMOVE
    )
    @JsonIgnoreProperties("reviewedUser")
    private List<RatingReview> ratingReviews;

    @OneToMany(
            mappedBy = "vendor",
            cascade = CascadeType.REFRESH
    )
    @JsonIgnoreProperties("vendor")
    private List<Task> tasks;

}


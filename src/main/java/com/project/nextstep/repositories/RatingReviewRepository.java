package com.project.nextstep.repositories;


import com.project.nextstep.entity.RatingReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingReviewRepository extends JpaRepository<RatingReview,Long> {

}

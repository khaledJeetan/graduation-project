package com.project.nextstep.controllers.account;
import com.project.nextstep.entity.RatingReview;
import com.project.nextstep.services.RatingReviewService;
import com.project.nextstep.services.account.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private SupplierService supplierService;
    private RatingReviewService reviewService;


    @Autowired
    public SupplierController(SupplierService supplierService, RatingReviewService reviewService) {
        this.supplierService = supplierService;
        this.reviewService = reviewService;
    }

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<RatingReview>> getAllProductReviews(
            @PathVariable("productId") Long productId
    ) {
        List<RatingReview> reviews = reviewService.getAllProductReviews( productId);
        return ResponseEntity.ok().body(reviews);
    }

}

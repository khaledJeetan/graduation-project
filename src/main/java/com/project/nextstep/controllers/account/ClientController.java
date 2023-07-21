package com.project.nextstep.controllers.account;


import com.project.nextstep.entity.RatingReview;
import com.project.nextstep.services.ConstructionService;
import com.project.nextstep.services.RatingReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {


    private final RatingReviewService reviewService;
    private final ConstructionService constructionService;

    @Autowired
    public ClientController(RatingReviewService reviewService, ConstructionService constructionService) {
        this.reviewService = reviewService;
        this.constructionService = constructionService;
    }

    @PostMapping("/{clientId}/suppliers/{supplierId}/products/{productId}/reviews")
    public ResponseEntity<RatingReview> addProductRatingReview(
            @PathVariable("clientId") Long clientId,
            @PathVariable("supplierId") Long supplierId,
            @PathVariable("productId") Long productId,
            @RequestBody RatingReview ratingReview) {

        RatingReview savedReview = reviewService.saveRatingReview(clientId,supplierId,productId,ratingReview);
        return savedReview != null ?  ResponseEntity.ok(savedReview) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<RatingReview> updateRatingReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody RatingReview ratingReview) {

        RatingReview updatedReview = reviewService.updateReview(reviewId,ratingReview);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<RatingReview> DeleteRatingReview(
            @PathVariable("reviewId") Long reviewId) {
        RatingReview DeletedReview = reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(DeletedReview);
    }

    @PostMapping("/{clientId}/vendors/{vendorId}/reviews")
    public ResponseEntity<RatingReview> addVendorRatingReview(
            @PathVariable("clientId") Long clientId,
            @PathVariable("vendorId") Long vendorId,
            @RequestBody RatingReview ratingReview) {

        RatingReview savedReview = reviewService.saveRatingReview( clientId, vendorId, ratingReview);
        return ResponseEntity.ok(savedReview);
    }

}

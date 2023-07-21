package com.project.nextstep.services;


import com.project.nextstep.entity.RatingReview;
import com.project.nextstep.entity.accounts.Client;
import com.project.nextstep.entity.accounts.Supplier;
import com.project.nextstep.entity.accounts.Vendor;
import com.project.nextstep.entity.construction.Product;
import com.project.nextstep.repositories.RatingReviewRepository;
import com.project.nextstep.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingReviewService {

    private final RatingReviewRepository reviewRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public RatingReviewService(RatingReviewRepository reviewRepository, UserService userService, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.productService = productService;
    }

    private Vendor getVendor(Long id){
        return (Vendor) userService.getUser(id);
    }
    private Supplier getSupplier(Long id){
        return (Supplier) userService.getUser(id);
    }
    private Product getProduct(Long id){
        return productService.getProduct(id);
    }
    private Client getClient(Long id) {return (Client) userService.getUser(id);}

    public List<RatingReview> getAllVendorReviews(Long vendorId) {
        return getVendor(vendorId).getRatingReviews();
    }

    public List<RatingReview> getAllProductReviews(Long productId) {
        return getProduct(productId).getRatingReviews();
    }

    @Transactional
    public RatingReview updateReview(Long reviewId, RatingReview review) {
        RatingReview oldReview = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalStateException("No Review with id "+reviewId+ " found. ERROR!!")
        );
        oldReview.setReview(review.getReview());
        oldReview.setRating(review.getRating());
        return oldReview;
    }

    public RatingReview saveRatingReview(Long clientId, Long supplierId, Long productId, RatingReview ratingReview) {
        Client client = getClient(clientId);
        Supplier supplier = getSupplier(supplierId);
        Product product = getProduct(productId);

        if(!product.getSupplier().equals(supplier)) {
            // The requested product does not belong to the requested supplier
            return null;
        }
        ratingReview.setReviewer(client);
        ratingReview.setProduct(product);
        reviewRepository.save(ratingReview);
        return ratingReview;
    }

    public RatingReview saveRatingReview(Long clientId, Long VendorId, RatingReview ratingReview) {
        ratingReview.setReviewer(getClient(clientId));
        ratingReview.setReviewedUser(getVendor(VendorId));
        reviewRepository.save(ratingReview);
        return ratingReview;
    }

    public RatingReview deleteReview(Long reviewId) {
        RatingReview review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalStateException("No Review with id "+reviewId+ " found. ERROR!!")
        );
        reviewRepository.deleteById(reviewId);
        return review;
    }
}

package com.microservices.review.controller;

import com.microservices.review.model.Review;
import com.microservices.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review) {
        if(reviewService.addReview(companyId, review))
            return ResponseEntity.ok("review added successfully!");
        return new ResponseEntity<>("there is no matching company id",
                                    HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null)
            return ResponseEntity.ok(review);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review) {
        if(reviewService.updateReview(reviewId, review))
            return ResponseEntity.ok("Review updated successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        if(reviewService.deleteReview(reviewId))
            return ResponseEntity.ok("Review deleted successfully!");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

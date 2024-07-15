package com.microservices.review.service;


import com.microservices.review.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(Long reviewId);
    boolean updateReview(Long reviewId, Review updatedReview);
    boolean deleteReview(Long reviewId);

}

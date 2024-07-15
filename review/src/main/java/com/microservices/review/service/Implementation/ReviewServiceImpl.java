package com.microservices.review.service.Implementation;

import com.microservices.review.model.Review;
import com.microservices.review.repository.ReviewRepository;
import com.microservices.review.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {

        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId,Review updatedReview) {
        Review oldReview = getReviewById(reviewId);
        if(oldReview != null) {
            oldReview.setDescription(updatedReview.getDescription());
            oldReview.setRating(updatedReview.getRating());
            oldReview.setTitle(updatedReview.getTitle());
            oldReview.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(oldReview);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }
}

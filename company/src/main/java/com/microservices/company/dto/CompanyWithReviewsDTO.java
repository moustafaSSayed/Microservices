package com.microservices.company.dto;

import com.microservices.company.external.Review;
import com.microservices.company.model.Company;

import java.util.List;

public class CompanyWithReviewsDTO {

    private Company company;
    private List<Review> reviews;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}

package com.microservices.company.model;

import jakarta.persistence.*;


@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
//    private Long reviewId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Long getReviewId() {
//        return reviewId;
//    }
//
//    public void setReviewId(Long reviewId) {
//        this.reviewId = reviewId;
//    }
}

package com.shashi.JobApplication.reviews.impl;

import com.shashi.JobApplication.companies.Company;
import com.shashi.JobApplication.companies.CompanyService;
import com.shashi.JobApplication.reviews.Review;
import com.shashi.JobApplication.reviews.ReviewController;
import com.shashi.JobApplication.reviews.ReviewRepo;
import com.shashi.JobApplication.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final CompanyService companyService;

     public ReviewServiceImpl(ReviewRepo reviewRepo,CompanyService companyService) {
        this.reviewRepo = reviewRepo;
        this.companyService=companyService;
    }
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepo.findByCompany_Id(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getById(companyId);
        if(companyId!=null){
            review.setCompany(company);
            reviewRepo.save(review);
        }else {
            throw new IllegalArgumentException("Company with id " + companyId + " not found.");
        }
        return true;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
         List<Review> reviews=getAllReviews(companyId);
         return reviews.stream()
                 .filter(review->review.getId().equals(reviewId))
                 .findFirst()
                 .orElse(null);
    }
    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {

        Review existingReview = reviewRepo.findById(reviewId).orElse(null);

        if (existingReview != null && companyService.getById(companyId) != null) {

            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setDescription(updatedReview.getDescription());
            existingReview.setRating(updatedReview.getRating());

            reviewRepo.save(existingReview);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReview(Long companyId,Long reviewId) {
        Review review = reviewRepo.findById(reviewId).orElse(null);

        if (review != null) {
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.update(companyId, company);
            reviewRepo.deleteById(reviewId);
            return true;
        }
        return false;

    }
}

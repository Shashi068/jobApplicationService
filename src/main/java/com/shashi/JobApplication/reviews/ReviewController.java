package com.shashi.JobApplication.reviews;

import com.shashi.JobApplication.companies.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company/{companyId}")
public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService revireService){
        this.reviewService=revireService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        Review review=reviewService.getReviewById(companyId,reviewId);
        return new ResponseEntity<>(review,HttpStatus.OK);
    }
    @PostMapping("/addReview")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
       boolean isReviewSaved= reviewService.addReview(companyId,review);
       if(isReviewSaved){
           return new ResponseEntity<>("Review added successfully",HttpStatus.CREATED);
         }else {
           return new ResponseEntity<>("Review not added", HttpStatus.BAD_REQUEST);
       }
    }
    @PutMapping("/updateReview/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review){
       boolean isUpdated=reviewService.updateReview(companyId,reviewId,review);
       if(isUpdated){
              return new ResponseEntity<>("Review Updated",HttpStatus.OK);
       }else {
              return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
         }
    }
    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId,@PathVariable Long companyId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isDeleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }

    }
}

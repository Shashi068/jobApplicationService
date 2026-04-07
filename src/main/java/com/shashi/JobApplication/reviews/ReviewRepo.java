package com.shashi.JobApplication.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByCompany_Id(Long companyId);
}

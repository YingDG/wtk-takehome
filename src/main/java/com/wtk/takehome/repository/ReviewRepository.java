package com.wtk.takehome.repository;

import com.wtk.takehome.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByHotelId(Long hotelId);
    
    List<Review> findByReviewer(String reviewer);
    
    List<Review> findByRating(Integer rating);
    
    long countByHotelId(Long hotelId);
}

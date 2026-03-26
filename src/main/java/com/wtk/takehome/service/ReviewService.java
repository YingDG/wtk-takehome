package com.wtk.takehome.service;

import com.wtk.takehome.dto.ReviewDto;
import com.wtk.takehome.entity.Hotel;
import com.wtk.takehome.entity.Review;
import com.wtk.takehome.repository.HotelRepository;
import com.wtk.takehome.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final HotelRepository hotelRepository;

    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Review save(ReviewDto reviewDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        review.setCreatedAt(LocalDate.now());

        Assert.notNull(reviewDto.getHotelId(), "Hotel ID cannot be null");
        Hotel hotel = hotelRepository.getReferenceById(reviewDto.getHotelId());
        Assert.notNull(hotel, "Hotel not found");
        review.setHotel(hotel);

        return reviewRepository.save(review);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> findByHotelId(Long hotelId) {
        return reviewRepository.findByHotelId(hotelId);
    }

    public List<Review> findByReviewer(String reviewer) {
        return reviewRepository.findByReviewer(reviewer);
    }

    public long countByHotelId(Long hotelId) {
        return reviewRepository.countByHotelId(hotelId);
    }
}

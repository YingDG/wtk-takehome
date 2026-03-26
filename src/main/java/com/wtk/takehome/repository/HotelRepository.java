package com.wtk.takehome.repository;

import com.wtk.takehome.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// ... existing code ...
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
    List<Hotel> findByCity(String city);
    
    List<Hotel> findByCountry(String country);
    
    List<Hotel> findByStars(Integer stars);
    
    List<Hotel> findByCityAndCountry(String city, String country);
    
    List<Hotel> findByNameContainingIgnoreCase(String name);
}

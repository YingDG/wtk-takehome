package com.wtk.takehome.service;

import com.wtk.takehome.entity.Hotel;
import com.wtk.takehome.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// ... existing code ...
@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> findByCity(String city) {
        return hotelRepository.findByCity(city);
    }

    public List<Hotel> findByCountry(String country) {
        return hotelRepository.findByCountry(country);
    }

    public List<Hotel> findByStars(Integer stars) {
        return hotelRepository.findByStars(stars);
    }

    public List<Hotel> findByNameContainingIgnoreCase(String name) {
        return hotelRepository.findByNameContainingIgnoreCase(name);
    }
}

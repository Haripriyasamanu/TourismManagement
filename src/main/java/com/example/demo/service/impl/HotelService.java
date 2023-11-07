package com.example.demo.service.impl;

import com.example.demo.model.Hotel;
import com.example.demo.repository.IHotelRepository;
import com.example.demo.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private IHotelRepository hotelRepository;

    @Override
    public Hotel getById(Long id) {
        return hotelRepository.findById(id).get();
    }

    @Override
    public List<Hotel> all() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel add(Hotel room, MultipartFile photo) {
        return hotelRepository.save(room);
    }

    @Override
    public Hotel update(Hotel room) {
        return hotelRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }
}

package com.example.demo.service.impl;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.IRestaurantRepository;
import com.example.demo.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;


    @Override
    public Restaurant getById(Long id) {
        return restaurantRepository.findById(id).get();
    }

    @Override
    public List<Restaurant> all() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant add(Restaurant room, MultipartFile photo) {
        return restaurantRepository.save(room);
    }

    @Override
    public Restaurant update(Restaurant room) {
        return restaurantRepository.save(room);
    }

    @Override
    public void deleteById(Long id) {
        restaurantRepository.deleteById(id);
    }

}

package com.example.demo.service;

import com.example.demo.model.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRestaurantService {

    Restaurant getById(Long id);
    List<Restaurant> all();
    Restaurant add(Restaurant room,MultipartFile photo);
    Restaurant update(Restaurant room);
    void deleteById(Long id);


}

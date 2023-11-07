package com.example.demo.service;



import com.example.demo.model.Hotel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHotelService {

    Hotel getById(Long id);
    List<Hotel> all();
    Hotel add(Hotel room, MultipartFile photo);
    Hotel update(Hotel room);
    void delete(Long id);
}

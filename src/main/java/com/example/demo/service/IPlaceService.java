package com.example.demo.service;



import com.example.demo.model.Place;
import com.example.demo.model.Travel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPlaceService {

    Place getById(Long id);
    List<Place> all();
    Place add(Place room, MultipartFile image);
    Place update(Place room);
    void delete(Long id);

    Travel getTravel(Long id);
    List<Travel> allTravels();
    Travel addTravel(Travel room,MultipartFile photo);
    Travel updateTravel(Travel room);
    void deleteTravel(Long id);

}

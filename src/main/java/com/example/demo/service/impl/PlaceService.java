package com.example.demo.service.impl;


import com.example.demo.model.Place;
import com.example.demo.model.Travel;
import com.example.demo.repository.IPlaceRepository;
import com.example.demo.repository.ITravelRepository;
import com.example.demo.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PlaceService implements IPlaceService {
    @Autowired
    private IPlaceRepository placeRepository;
    @Autowired
    private ITravelRepository travelRepository;

    @Override
    public Place getById(Long id) {
        return placeRepository.findById(id).get();
    }

    @Override
    public List<Place> all() {
        return placeRepository.findAll();
    }

    @Override
    public Place add(Place room, MultipartFile image) {
        return placeRepository.save(room);
    }

    @Override
    public Place update(Place room) {
        return placeRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public Travel getTravel(Long id) {
        return travelRepository.findById(id).get();
    }

    @Override
    public List<Travel> allTravels() {
        return travelRepository.findAll();
    }

    @Override
    public Travel addTravel(Travel room,MultipartFile photo) {
        return travelRepository.save(room);
    }

    @Override
    public Travel updateTravel(Travel room) {
        return travelRepository.save(room);
    }

    @Override
    public void deleteTravel(Long id) {
        travelRepository.deleteById(id);
    }
}

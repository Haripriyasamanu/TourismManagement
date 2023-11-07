package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PlaceController {

    @Autowired
    private IPlaceService placeService;
    @Autowired
    private IHotelService hotelService;
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private IGuideService guideService;


    @PostMapping("/addHotel/{id}/{hId}")
    private ResponseEntity<?> addHotel(@PathVariable Long id, @PathVariable Long hId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Place place = placeService.getById(id);
            Hotel hotel = hotelService.getById(hId);
            place.getHotels().add(hotel);
            return new ResponseEntity<>(placeService.update(place), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addRest/{id}/{rId}")
    private ResponseEntity<?> addRestaurant(@PathVariable Long id, @PathVariable Long rId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Place place = placeService.getById(id);
            Restaurant restaurant = restaurantService.getById(rId);
            place.getRestaurants().add(restaurant);
            return new ResponseEntity<>(placeService.update(place), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addTravel/{id}/{tId}")
    private ResponseEntity<?> addTravel(@PathVariable Long id, @PathVariable Long tId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Place place = placeService.getById(id);
            Travel travel = placeService.getTravel(tId);
            place.getTravels().add(travel);
            return new ResponseEntity<>(placeService.update(place), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addGuide/{id}/{gId}")
    private ResponseEntity<?> addGuide(@PathVariable Long id, @PathVariable Long gId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Place place = placeService.getById(id);
            Guide guide = guideService.getById(gId);
            place.getGuides().add(guide);
            return new ResponseEntity<>(placeService.update(place), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/places")
    private ResponseEntity<?> addPlace(String name, String history, String location, MultipartFile image) {
        try {
            Place place = Place.builder()
                    .name(name)
                    .history(history)
                    .location(location)
                    .image(Base64.getEncoder().encodeToString(image.getBytes()))
                    .build();
            placeService.add(place, image);
            return new ResponseEntity<>("place added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/places")
    private ResponseEntity<?> allProducts() {
        try {
            return new ResponseEntity<>(placeService.all(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/places/{id}")
    private ResponseEntity<?> productGetById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(placeService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/places/{id}")
    private ResponseEntity<?> updateProduct(@PathVariable Long id, MultipartFile image, String name, String history, String location) {
        try {
            Place p = placeService.getById(id);
            Place place = Place.builder()
                    .name(name)
                    .history(history)
                    .location(location)
                    .image(Base64.getEncoder().encodeToString(image.getBytes()))
                    .build();
            p.setName(place.getName());
            p.setHistory(place.getHistory());
            p.setLocation(place.getLocation());
            p.setImage(place.getImage());
            return new ResponseEntity<>(placeService.update(p), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/places/{id}")
    private ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            placeService.delete(id);
            return new ResponseEntity<>("details deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

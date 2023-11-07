package com.example.demo.controller;


import com.example.demo.model.Hotel;
import com.example.demo.model.Place;
import com.example.demo.service.IHotelService;
import com.example.demo.service.IPlaceService;
import com.example.demo.utils.Validator;
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
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IPlaceService placeService;

    @GetMapping("/hotel/{id}")
    private ResponseEntity<?> hotel(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Place place = placeService.getById(id);
            return new ResponseEntity<>(hotelService.all().stream().filter(i -> i.getPlaceId().equals(place.getId())), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/hotels")
    private ResponseEntity<?> postProduct(String name, Long placeId, String type, String rating, String description, String inTime, String outTime, String location, MultipartFile photo) {
        HashMap<String, String> res = new HashMap<>();
        try {

            if (!Validator.validateName(name)) {
                res.put("msg", "Name should starts with capital");
                return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
            }
            if (rating.length() > 5) {
                res.put("msg", "Review should be up to Five stars");
                return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
            }
            Hotel hotel = Hotel.builder()
                    .placeId(placeId)
                    .name(name)
                    .type(type)
                    .rating(rating)
                    .description(description)
                    .inTime(inTime)
                    .outTime(outTime)
                    .location(location)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            return new ResponseEntity<>(hotelService.add(hotel, photo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/hotels")
    private ResponseEntity<?> allProducts() {
        try {
            return new ResponseEntity<>(hotelService.all(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/hotels/{id}")
    private ResponseEntity<?> productGetById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(hotelService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/hotels/{id}")
    private ResponseEntity<?> updateProduct(@PathVariable Long id, Long placeId, String name, String type, String rating, String description, String inTime, String outTime, String location, MultipartFile photo) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Hotel hotel = hotelService.getById(id);
            if (!Validator.validateName(name)) {
                res.put("msg", "Name should starts with capital");
                return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
            }
            if (rating.length() > 5) {
                res.put("msg", "Review should be up to Five stars");
                return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
            }
            Hotel h = Hotel.builder()
                    .placeId(placeId)
                    .name(name)
                    .type(type)
                    .rating(rating)
                    .description(description)
                    .inTime(inTime)
                    .outTime(outTime)
                    .location(location)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            hotel.setPlaceId(h.getPlaceId());
            hotel.setName(h.getName());
            hotel.setType(h.getType());
            hotel.setRating(h.getRating());
            hotel.setDescription(h.getDescription());
            hotel.setInTime(h.getInTime());
            hotel.setOutTime(h.getOutTime());
            hotel.setLocation(h.getLocation());
            hotel.setPhoto(h.getPhoto());
            return new ResponseEntity<>(hotelService.update(hotel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hotel/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            hotelService.delete(id);
            return new ResponseEntity<>("Hotel deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/hotels/{id}/{placeId}")
    private ResponseEntity<?> deleteHotel(@PathVariable Long id, @PathVariable Long placeId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Place place = placeService.getById(placeId);
            Hotel hotel = hotelService.getById(id);
            place.getHotels().remove(hotel);
            hotelService.delete(hotel.getId());
            placeService.update(place);
            return new ResponseEntity<>("Hotel deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

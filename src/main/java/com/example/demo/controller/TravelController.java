package com.example.demo.controller;

import com.example.demo.model.Place;

import com.example.demo.model.Travel;
import com.example.demo.service.IPlaceService;
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
public class TravelController {

    @Autowired
    private IPlaceService placeService;

    @PostMapping("/travels")
    private ResponseEntity<?> postProduct(String day,Long placeId ,String type, String timings, MultipartFile photo) {
        try {

            Travel travel = Travel.builder()
                    .placeId(placeId)
                    .day(day)
                    .type(type)
                    .timings(timings)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            return new ResponseEntity<>(placeService.addTravel(travel, photo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/travel/{id}")
    private ResponseEntity<?> travel(@PathVariable Long id){
        HashMap<String,String > res= new HashMap<>();
        try {
            Place place=placeService.getById(id);
            return  new ResponseEntity<>(placeService.allTravels().stream().filter(i->i.getPlaceId().equals(place.getId())),HttpStatus.OK);
        }catch (Exception e){
            res.put("msg",e.getMessage());
            return  new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/travels")
    private ResponseEntity<?> allProducts() {
        try {
            return new ResponseEntity<>(placeService.allTravels(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/travels/{id}")
    private ResponseEntity<?> productGetById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(placeService.getTravel(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/travels/{id}")
    private ResponseEntity<?> updateProduct(@PathVariable Long id,Long placeId, String day, String type, String timings, MultipartFile photo) {
        try {
            Travel place = placeService.getTravel(id);

            Travel travel = Travel.builder()
                    .placeId(placeId)
                    .day(day)
                    .type(type)
                    .timings(timings)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            place.setDay(travel.getDay());
            place.setType(travel.getType());
            place.setTimings(travel.getTimings());
            place.setPhoto(travel.getPhoto());
            return new ResponseEntity<>(placeService.updateTravel(place), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/travels/{id}/{pId}")
    private ResponseEntity<?> deleteProduct(@PathVariable Long id, @PathVariable Long pId) {
        try {
            Place place=placeService.getById(pId);
            Travel travel=placeService.getTravel(id);
            place.getTravels().remove(travel);
            placeService.deleteTravel(travel.getId());
            placeService.update(place);
            return new ResponseEntity<>("details deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.IHotelService;
import com.example.demo.service.IPlaceService;
import com.example.demo.service.IRestaurantService;
import com.example.demo.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1")
public class ReviewController {
    @Autowired
    private IReviewService reviewService;
    @Autowired
    private IHotelService hotelService;
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private IPlaceService placeService;

    @PostMapping("/reviews")
    private ResponseEntity<?> postProduct(@RequestBody Review review) {
        try {
            return new ResponseEntity<>(reviewService.add(review), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/placeReviews/{id}")
    private ResponseEntity<?> placeReviews(@PathVariable Long id){
        HashMap<String,String> res= new HashMap<>();
        try{
            Place place=placeService.getById(id);
            return  new ResponseEntity<>(reviewService.all().stream().filter(i->i.getPlaceId().equals(place.getId())),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reviews")
    private ResponseEntity<?> allProducts() {
        try {
            return new ResponseEntity<>(reviewService.all(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reviews/{id}")
    private ResponseEntity<?> productGetById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(reviewService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/reviews/{id}")
    private ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Review guide) {
        try {
            return new ResponseEntity<>(reviewService.update(guide), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping("/reviews/{id}/{pId}")
//    private ResponseEntity<?> deleteProduct( @PathVariable  Long id,@PathVariable Long pId){
//        try {
//            Place place=placeService.getById(pId);
//            Guide guide=guideService.getById(id);
//            place.getGuides().remove(guide);
//            placeService.update(place);
//            return new ResponseEntity<>("details deleted successfully",HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //    @GetMapping("/addToHotel/{id}/{rId}")
//    private ResponseEntity<?> addToHotel(@PathVariable Long id, @PathVariable Long hId) {
//        HashMap<String, String> res = new HashMap<>();
//        try {
//            Hotel hotel = hotelService.getById(id);
//            Review review = reviewService.getById(hId);
//            hotel.getReviews().add(review);
//            return new ResponseEntity<>(hotelService.update(hotel), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/addToRest/{id}/{rId}")
//    private ResponseEntity<?> addToRest(@PathVariable Long id, @PathVariable Long rId) {
//        HashMap<String, String> res = new HashMap<>();
//        try {
//            Restaurant restaurant = restaurantService.getById(rId);
//            Review review = reviewService.getById(id);
//            restaurant.getReviews().add(review);
//            return new ResponseEntity<>(restaurantService.update(restaurant), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/addToTravel/{id}/{rId}")
//    private ResponseEntity<?> addToTravel(@PathVariable Long id, @PathVariable Long rId) {
//        HashMap<String, String> res = new HashMap<>();
//        try {
//            Travel travel = placeService.getTravel(rId);
//            Review review = reviewService.getById(id);
//            travel.getReviews().add(review);
//            return new ResponseEntity<>(placeService.updateTravel(travel), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/hotelReviews/{id}")
//    private ResponseEntity<?> guide(@PathVariable Long id) {
//        HashMap<String, String> res = new HashMap<>();
//        try {
//            Hotel hotel = hotelService.getById(id);
//            return new ResponseEntity<>(reviewService.all().stream().filter(i -> i.getHotelId().equals(hotel.getId())), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/restReviews/{id}")
//    private ResponseEntity<?> restReviews(@PathVariable Long id) {
//        HashMap<String, String> res = new HashMap<>();
//        try {
//            Restaurant restaurant = restaurantService.getById(id);
//            return new ResponseEntity<>(reviewService.all().stream().filter(i -> i.getRestId().equals(restaurant.getId())), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/travelReviews/{id}")
//    private ResponseEntity<?> travelReviews(@PathVariable Long id) {
//        HashMap<String, String> res = new HashMap<>();
//        try {
//            Travel travel = placeService.getTravel(id);
//            return new ResponseEntity<>(reviewService.all().stream().filter(i -> i.getTravelId().equals(travel.getId())), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}

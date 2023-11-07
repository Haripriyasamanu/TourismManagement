package com.example.demo.controller;

import com.example.demo.model.Place;
import com.example.demo.model.Restaurant;

import com.example.demo.service.IHotelService;
import com.example.demo.service.IPlaceService;
import com.example.demo.service.IRestaurantService;
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
public class RestaurantController {
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private IPlaceService placeService;
    @Autowired
    private IHotelService hotelService;

    @PostMapping("/restaurants")
    private ResponseEntity<?> postProduct(Long placeId,String name, String foodType, String rating, String description, String inTime,String outTime,String location, MultipartFile photo){
        HashMap<String,String> res= new HashMap<>();
        try {
            if (!Validator.validateName(name)){
                res.put("msg","Name should starts with capital");
                return  new ResponseEntity<>(res,HttpStatus.EXPECTATION_FAILED);
            }
            if (rating.length()>5){
                res.put("msg","Review should be up to Five stars");
                return  new ResponseEntity<>(res,HttpStatus.EXPECTATION_FAILED);
            }
            Restaurant restaurant= Restaurant.builder()
                    .placeId(placeId)
                    .name(name)
                    .foodType(foodType)
                    .rating(rating)
                    .description(description)
                    .inTime(inTime)
                    .outTime(outTime)
                    .location(location)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            return new ResponseEntity<>(restaurantService.add(restaurant,photo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/restaurant/{id}")
    private ResponseEntity<?> restaurant(@PathVariable Long id){
        HashMap<String,String> res= new HashMap<>();
        try {
            Place place= placeService.getById(id);
            return  new ResponseEntity<>(restaurantService.all().stream().filter(i->i.getPlaceId().equals(place.getId())),HttpStatus.OK);
        }catch (Exception e){
            res.put("msg",e.getMessage());
            return  new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/restaurants")
    private ResponseEntity<?> allProducts(){
        try {
            return new ResponseEntity<>(restaurantService.all(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/restaurants/{id}")
    private ResponseEntity<?> productGetById(@PathVariable Long id){
        try {
            return  new ResponseEntity<>(restaurantService.getById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/restaurants/{id}")
    private ResponseEntity<?> updateProduct(@PathVariable Long id,Long placeId,String name,String rating,String description,String foodType,String inTime,String outTime,String location,MultipartFile photo){
        try {
            HashMap<String,String> res= new HashMap<>();
            Restaurant place=restaurantService.getById(id);
            if (!Validator.validateName(name)){
                res.put("msg","Name should starts with capital");
                return  new ResponseEntity<>(res,HttpStatus.EXPECTATION_FAILED);
            }
            if (rating.length()>5){
                res.put("msg","Review should be up to Five stars");
                return  new ResponseEntity<>(res,HttpStatus.EXPECTATION_FAILED);
            }
            Restaurant rest= Restaurant.builder()
                    .placeId(placeId)
                    .name(name)
                    .rating(rating)
                    .description(description)
                    .foodType(foodType)
                    .inTime(inTime)
                    .outTime(outTime)
                    .location(location)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            place.setPlaceId(rest.getPlaceId());
            place.setName(rest.getName());
            place.setRating(rest.getRating());
            place.setDescription(rest.getDescription());
            place.setInTime(rest.getInTime());
            place.setOutTime(rest.getOutTime());
            place.setLocation(rest.getLocation());
            place.setPhoto(rest.getPhoto());
            place.setFoodType(rest.getFoodType());
            return new ResponseEntity<>(restaurantService.update(place),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/restaurants/{id}")
    private ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            restaurantService.deleteById(id);
            return new ResponseEntity<>("details deleted successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/restaurant/{id}/{pId}")
    private ResponseEntity<?> delete(@PathVariable Long id,@PathVariable Long pId){
        HashMap<String,String> res= new HashMap<>();
        try {
            Place place=placeService.getById(pId);
            Restaurant restaurant=restaurantService.getById(id);
            place.getRestaurants().remove(restaurant);
            restaurantService.deleteById(restaurant.getId());
            placeService.update(place);
            res.put("msg","Restaurant deleted successfully");
            return  new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            res.put("msg",e.getMessage());
            return  new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

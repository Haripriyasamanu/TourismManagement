package com.example.demo.controller;

import com.example.demo.model.Guide;
import com.example.demo.model.Place;

import com.example.demo.service.IGuideService;
import com.example.demo.service.IPlaceService;
import com.example.demo.service.impl.PlaceService;
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
public class GuideController {

    @Autowired
    private IGuideService guideService;
    @Autowired
    private IPlaceService placeService;

    @PostMapping("/guides")
    private ResponseEntity<?> postProduct(Long placeId, String name, String contactNumber, MultipartFile photo) {
        HashMap<String, String> res = new HashMap<>();
        try {
            if (!Validator.validateName(name)) {
                res.put("msg", "Name should starts with capital");
                return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
            }
            if (!Validator.validNumber(contactNumber)) {
                res.put("msg", "Number should 10 numbers only");
                return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
            }
            Guide guide = Guide.builder()
                    .placeId(placeId)
                    .name(name)
                    .contactNumber(contactNumber)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            return new ResponseEntity<>(guideService.add(guide, photo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/guide/{id}")
    private ResponseEntity<?> guide(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Place place = placeService.getById(id);
            return new ResponseEntity<>(guideService.all().stream().filter(i -> i.getPlaceId().equals(place.getId())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/guides")
    private ResponseEntity<?> allProducts() {
        try {
            return new ResponseEntity<>(guideService.all(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/guides/{id}")
    private ResponseEntity<?> productGetById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(guideService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/guides/{id}")
    private ResponseEntity<?> updateProduct(@PathVariable Long id, Long placeId, String name, String contactNumber, MultipartFile photo) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Guide guide = guideService.getById(id);
            Guide guide1 = Guide.builder()
                    .placeId(placeId)
                    .name(name)
                    .contactNumber(contactNumber)
                    .photo(Base64.getEncoder().encodeToString(photo.getBytes()))
                    .build();
            guide.setName(guide1.getName());
            guide.setContactNumber(guide1.getContactNumber());
            guide.setPlaceId(guide1.getPlaceId());
            guide.setPhoto(guide1.getPhoto());
            return new ResponseEntity<>(guideService.update(guide), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/guides/{id}/{pId}")
    private ResponseEntity<?> deleteProduct(@PathVariable Long id, @PathVariable Long pId) {
        try {
            Place place = placeService.getById(pId);
            Guide guide = guideService.getById(id);
            place.getGuides().remove(guide);
            placeService.update(place);
            return new ResponseEntity<>("details deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

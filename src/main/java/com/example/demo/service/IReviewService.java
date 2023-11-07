package com.example.demo.service;

import com.example.demo.model.Review;

import java.util.List;

public interface IReviewService {
    Review getById(Long id);
    List<Review> all();
    Review add(Review room);
    Review update(Review room);
    void delete(Long id);
}

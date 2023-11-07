package com.example.demo.service.impl;

import com.example.demo.model.Review;
import com.example.demo.repository.IReviewRepository;
import com.example.demo.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private IReviewRepository reviewRepository;
    @Override
    public Review getById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public List<Review> all() {
        return reviewRepository.findAll();
    }

    @Override
    public Review add(Review room) {
        return reviewRepository.save(room);
    }

    @Override
    public Review update(Review room) {
        return reviewRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}

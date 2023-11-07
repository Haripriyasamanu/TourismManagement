package com.example.demo.service.impl;

import com.example.demo.model.Guide;
import com.example.demo.repository.IGuideRepository;
import com.example.demo.service.IGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GuideService implements IGuideService {

    @Autowired
    private IGuideRepository guideRepository;

    @Override
    public Guide getById(Long id) {
        return guideRepository.findById(id).get();
    }

    @Override
    public List<Guide> all() {
        return guideRepository.findAll();
    }

    @Override
    public Guide add(Guide room,MultipartFile photo) {
        return guideRepository.save(room);
    }

    @Override
    public Guide update(Guide room) {
        return guideRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        guideRepository.deleteById(id);
    }
}

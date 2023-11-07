package com.example.demo.service;

import com.example.demo.model.Guide;
import com.example.demo.model.Hotel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGuideService {
    Guide getById(Long id);
    List<Guide> all();
    Guide add(Guide room, MultipartFile photo);
    Guide update(Guide room);
    void delete(Long id);
}

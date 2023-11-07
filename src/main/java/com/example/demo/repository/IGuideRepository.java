package com.example.demo.repository;

import com.example.demo.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface IGuideRepository extends JpaRepository<Guide,Long> {

}

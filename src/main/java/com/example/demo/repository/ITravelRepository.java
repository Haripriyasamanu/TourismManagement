package com.example.demo.repository;

import com.example.demo.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravelRepository extends JpaRepository<Travel,Long> {

}

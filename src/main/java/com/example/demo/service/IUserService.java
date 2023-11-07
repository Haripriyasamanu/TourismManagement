package com.example.demo.service;

import com.example.demo.model.User;


import java.util.List;

public interface IUserService {
    List<User> all();
    User getById(Long id);
    User add(User user);
    User update(User user);
    void delete(Long id);
    User getByEmail(String email);
    User getByEmailAndPassword(String email,String password);
}

package com.example.weatherapplication.service;

import com.example.weatherapplication.model.Role;
import com.example.weatherapplication.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname);
}

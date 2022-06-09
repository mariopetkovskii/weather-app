package com.example.weatherapplication.service.impl;

import com.example.weatherapplication.model.User;
import com.example.weatherapplication.model.exceptions.InvalidArgumentException;
import com.example.weatherapplication.model.exceptions.InvalidUserCredentialException;
import com.example.weatherapplication.model.exceptions.PasswordDoNotMatchException;
import com.example.weatherapplication.model.exceptions.UsernameAlreadyExistsException;
import com.example.weatherapplication.repository.jpa.UserRepository;
import com.example.weatherapplication.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException("Invalid arguments!");
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialException::new);
    }

}

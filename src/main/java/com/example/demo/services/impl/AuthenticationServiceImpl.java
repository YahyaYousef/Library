package com.example.demo.services.impl;

import com.example.demo.repo.UserRepo;
import com.example.demo.services.AuthenticationService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepo userRepo;

    public AuthenticationServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetailsService userDetailsService() {
        return email -> userRepo.findUserByUsername(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

}

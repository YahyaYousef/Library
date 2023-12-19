package com.example.demo.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService {


    UserDetailsService userDetailsService();

}

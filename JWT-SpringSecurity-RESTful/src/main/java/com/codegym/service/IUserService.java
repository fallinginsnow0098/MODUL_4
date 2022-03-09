package com.codegym.service;

import com.codegym.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Optional<User> findByUsername(String username);
}

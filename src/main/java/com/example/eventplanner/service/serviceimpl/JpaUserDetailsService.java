package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.exception.UserNotFoundException;
import com.example.eventplanner.model.SecurityUser;
import com.example.eventplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsernameEquals(username);
        return user.map(SecurityUser::new).orElseThrow(()->new UserNotFoundException("Username not found for " + username));
    }
}
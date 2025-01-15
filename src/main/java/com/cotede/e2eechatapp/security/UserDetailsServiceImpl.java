package com.cotede.e2eechatapp.security;

import com.cotede.e2eechatapp.exceptions.CustomExceptions;
import com.cotede.e2eechatapp.users.User;
import com.cotede.e2eechatapp.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException(username));
        return user ;
    }

}
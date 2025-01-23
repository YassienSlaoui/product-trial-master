package com.product.trialback.services.impl;

import com.product.trialback.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Map.Entry<String, UserEntity>> userOp = UserServiceImpl.getUsers().entrySet().stream().filter(x -> x.getValue().getUsername().equals(username)).findFirst();

        if (userOp.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserServiceImpl.getUsers().get(userOp.get().getValue().getMail());

    }
}

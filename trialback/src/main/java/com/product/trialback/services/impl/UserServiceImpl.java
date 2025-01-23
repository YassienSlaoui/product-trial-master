package com.product.trialback.services.impl;


import com.product.trialback.config.JwtUtil;
import com.product.trialback.dto.Account;
import com.product.trialback.dto.AuthRequest;
import com.product.trialback.model.UserEntity;
import com.product.trialback.services.UserService;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Getter
    private static Map<String, UserEntity> users = new HashMap<>();

    public UserServiceImpl(
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {

        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String createAuthenticationToken(AuthRequest authRequest) {
        Optional<Map.Entry<String, UserEntity>> userOp = users.entrySet().stream().filter(x -> x.getValue().getUsername().equals(authRequest.getUsername())).findFirst();

        if (userOp.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }
        UserDetails userDetails = users.get(userOp.get().getValue().getMail());

        if (userDetails != null && passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            return jwtUtil.generateToken(userDetails);
        }

        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public String createAccount(Account account) {
        if (users.containsKey(account.getEmail())) {
            return "Account with email " + account.getEmail() + " is already exist";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(account.getUsername());
        userEntity.setMail(account.getEmail());
        userEntity.setFirstName(account.getFirstName());
        userEntity.setPassword(passwordEncoder.encode(account.getPassword()));

        users.put(userEntity.getMail(), userEntity);
        return "Done";
    }
   static Map<String, UserEntity> users(){
        return users;
   }
}

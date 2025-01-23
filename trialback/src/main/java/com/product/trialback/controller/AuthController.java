package com.product.trialback.controller;


import com.product.trialback.dto.Account;
import com.product.trialback.dto.AuthRequest;
import com.product.trialback.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/token")
    public String login(@RequestBody AuthRequest authRequest) {
        return userService.createAuthenticationToken(authRequest);
    }

    @PostMapping("/account")
    public String createAccount(@RequestBody Account account) {
        return userService.createAccount(account);
    }


}

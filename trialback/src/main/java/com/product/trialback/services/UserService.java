package com.product.trialback.services;


import com.product.trialback.dto.Account;
import com.product.trialback.dto.AuthRequest;


public interface UserService  {


    String createAuthenticationToken (AuthRequest authRequest);
    String createAccount (Account account);
}

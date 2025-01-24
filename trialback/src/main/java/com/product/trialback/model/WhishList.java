package com.product.trialback.model;


import lombok.Data;

import java.util.Set;
@Data
public class WhishList {
    private String username;
    private Set<Integer> productSet;
}

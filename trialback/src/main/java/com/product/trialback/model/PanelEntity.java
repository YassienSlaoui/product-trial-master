package com.product.trialback.model;

import lombok.Data;

import java.util.Map;
@Data
public class PanelEntity {

    String username;

    Map<Product,Integer> poductMap;
}

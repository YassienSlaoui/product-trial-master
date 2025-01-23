package com.product.trialback.model;

import lombok.Data;

import java.util.Map;
@Data
public class PanelEntity {

    UserEntity userEntity;

    Map<Integer,Integer> poductMap;
}

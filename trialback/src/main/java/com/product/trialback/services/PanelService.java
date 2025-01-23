package com.product.trialback.services;

import com.product.trialback.model.PanelEntity;
import com.product.trialback.model.Product;
import com.product.trialback.model.UserEntity;

public interface PanelService {

    PanelEntity panelList(UserEntity userEntity);

    PanelEntity addToPanelList(Integer productId, Integer number);

    PanelEntity removeFromPanelList(Integer productId , Integer number);

    PanelEntity dropPanelList();
}

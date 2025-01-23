package com.product.trialback.services;

import com.product.trialback.model.PanelEntity;
import com.product.trialback.model.Product;
import com.product.trialback.model.UserEntity;

public interface PanelService {

    PanelEntity panelList(String userId);

    PanelEntity addToPanelList(Integer productId, Integer number ,String user);

    PanelEntity removeFromPanelList(Integer productId , Integer number,String user);

    PanelEntity dropPanelList(String user);
}

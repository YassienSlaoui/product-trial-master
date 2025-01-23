package com.product.trialback.services.impl;

import com.product.trialback.model.PanelEntity;
import com.product.trialback.model.Product;
import com.product.trialback.model.UserEntity;
import com.product.trialback.services.PanelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PanelServiceImpl implements PanelService {

    List<PanelEntity> panelEntities = new ArrayList<>();

    @Override
    public PanelEntity panelList(String userId) {
        for (PanelEntity panelEntity : panelEntities) {
            if (panelEntity.getUsername().equals(userId)) {
               return panelEntity;
            }
        }
        return null;
    }

    @Override
    public PanelEntity addToPanelList(Integer productId, Integer number,String user) {
        return null;
    }

    @Override
    public PanelEntity removeFromPanelList(Integer productId, Integer number,String user) {
        return null;
    }

    @Override
    public PanelEntity dropPanelList(String user) {
        return null;
    }
}

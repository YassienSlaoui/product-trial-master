package com.product.trialback.services;

import com.product.trialback.dto.ProductPanelDTO;

import java.util.List;

public interface PanelService {

    List<ProductPanelDTO> panelList(String userId);

    List<ProductPanelDTO> addToPanelList(Integer productId, Integer number , String user);

    List<ProductPanelDTO> removeFromPanelList(Integer productId , Integer number, String user);

    List<ProductPanelDTO> dropPanelList(String user);
}

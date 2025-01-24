package com.product.trialback.services.impl;

import com.product.trialback.dto.ProductPanelDTO;
import com.product.trialback.model.PanelEntity;
import com.product.trialback.services.PanelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PanelServiceImpl implements PanelService {

    private final ProductServiceImpl productService;

    List<PanelEntity> panelEntities = new ArrayList<>();

    public PanelServiceImpl(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductPanelDTO> panelList(String userId) {
        for (PanelEntity panelEntity : panelEntities) {
            if (panelEntity.getUsername().equals(userId)) {
                return getPanel(panelEntity);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<ProductPanelDTO> addToPanelList(Integer productId, Integer number, String user) {

        boolean panelFound = false;

        for (PanelEntity panelEntity : panelEntities) {
            if (panelEntity.getUsername().equals(user)) {
                Map<Integer, Integer> map = panelEntity.getPoductMap();
                if (map.containsKey(productId)) {
                    int actuelNum = map.get(productId);
                    map.put(productId, actuelNum + number);
                } else {
                    map.put(productId, number);
                }
                panelFound = true;
                break;
            }
        }
        if (!panelFound) {
            PanelEntity panelEntity = new PanelEntity();
            panelEntity.setUsername(user);
            Map<Integer, Integer> products = new HashMap<>();
            products.put(productId, number);
            panelEntity.setPoductMap(products);
            panelEntities.add(panelEntity);
            return getPanel(panelEntity);
        }

        // If panel was found and updated, return the updated panel
        return getPanel(panelEntities.stream()
                .filter(panel -> panel.getUsername().equals(user))
                .findFirst()
                .orElseThrow());
    }

    @Override
    public List<ProductPanelDTO> removeFromPanelList(Integer productId, Integer number, String user) {
        for (PanelEntity panelEntity : panelEntities) {
            if (panelEntity.getUsername().equals(user)) {

                Map<Integer, Integer> map = panelEntity.getPoductMap();
                if (map.containsKey(productId)) {
                    int actuelNum = map.get(productId);
                    if (actuelNum > number) {
                        map.put(productId, actuelNum - number);
                    } else if (actuelNum == number) {
                        map.remove(productId);
                    } else {
                        throw new RuntimeException("You don't have that number of product in panel");
                    }
                    return getPanel(panelEntity);
                }
                throw new RuntimeException("You don't have that product in panel");
            }
        }
        throw new RuntimeException("You don't have any product in your panel");
    }

    @Override
    public List<ProductPanelDTO> dropPanelList(String user) {
        panelEntities.removeIf(panelEntity -> panelEntity.getUsername().equals(user));
        return new ArrayList<>();
    }

    public List<ProductPanelDTO> getPanel(PanelEntity panelEntity) {
        List<ProductPanelDTO> productPanelDTOS = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : panelEntity.getPoductMap().entrySet()) {
            ProductPanelDTO productPanelDTO = new ProductPanelDTO();
            productPanelDTO.setProductDTO(productService.getProduct(entry.getKey()));
            productPanelDTO.setQuantity(entry.getValue());
            productPanelDTOS.add(productPanelDTO);
        }
        return productPanelDTOS;
    }
}

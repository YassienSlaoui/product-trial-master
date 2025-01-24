package com.product.trialback.controller;

import com.product.trialback.dto.ProductPanelDTO;
import com.product.trialback.services.PanelService;
import jakarta.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/panel")
public class PanelController {
    private final PanelService panelService;

    public PanelController(PanelService panelService) {
        this.panelService = panelService;
    }


    @GetMapping
    public List<ProductPanelDTO> getPanel(Principal principal){
        return panelService.panelList(principal.getName());
    }
    @PostMapping
    public List<ProductPanelDTO> addToPanelList(@QueryParam("productId") Integer productId,@QueryParam("number") Integer number, Principal principal){
        return panelService.addToPanelList(productId,number,principal.getName());
    }
    @DeleteMapping
    public List<ProductPanelDTO> removeFromPanelList(@QueryParam("productId") Integer productId,@QueryParam("number") Integer number, Principal principal){
        return panelService.removeFromPanelList(productId,number,principal.getName());

    }
    @DeleteMapping("/drop")
    public List<ProductPanelDTO> dropPanelList(Principal principal){
        return panelService.dropPanelList(principal.getName());
    }
}
